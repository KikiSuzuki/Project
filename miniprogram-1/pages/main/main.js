// pages/main/main.js
const app = getApp()

Page({
  /**
   * Page initial data
   */
  data: {
    req_lvl: 3,
    req_txt:'',
    red: "activ",
    yellow: "nonactiv",
    green: "nonactiv",
    mylongitude:'',
    mylatitude:'',
    time:'',
    message:'',
    private: 1,
    counter: 0,
    items: [
      { name: 'Public', value: '0' },
      { name: 'Only Friends', value: '1', checked: 'true' },
    ]
  },

  /**
   * Lifecycle function--Called when page load
   */
  onLoad: function (options) {
    console.log(app.globalData.myUserInfo.user_id);
    console.log(app.globalData.userInfo.avatarUrl)
    wx.sendSocketMessage({
      data: '{ param:openSocket, user_id:' + app.globalData.myUserInfo.user_id + '}'
    })
  },
  /**
   * Lifecycle function--Called when page show
   */
  onShow: function () {
    self = this
    wx.onSocketMessage(function (res) {
      console.log("ONSOCKETMESSAGE  " + res.data)
      console.log("ONSOCKETMESSAGE  " + JSON.parse(res.data).param)
      self.getLoc();
      if (JSON.parse(res.data).param == "request") {
        console.log("It is req")
        const mylatitude1 = JSON.parse(res.data).mylatitude
        const mylongitude1 = JSON.parse(res.data).mylongitude
        const req_id = JSON.parse(res.data).request_id
        wx.showModal({
          title: 'Your Friend ' + JSON.parse(res.data).user_name + ' need a help',
          content: JSON.parse(res.data).txt + " " + self.haversine_km(mylatitude1, mylongitude1, self.data.mylatitude, self.data.mylongitude) + "km from you",
          cancelText: "ignore",
          confirmText: "location",
          success(res) {
            if (res.confirm) {
              console.log('"OK" is tapped')
              wx.openLocation({
                latitude: Number(mylatitude1),
                longitude: Number(mylongitude1),
                scale: 20
              })
              wx.showModal({
                title: 'Can you help?',
                content: 'Press Yes if you can help or No if can not',
                cancelText: "no",
                confirmText: "yes",
                success(res) {
                  if (res.confirm) {
                    wx.sendSocketMessage({
                      data: '{param:res, user_id:' + app.globalData.myUserInfo.user_id + ', request_id:' + req_id + '}',
                    }),
                    app.globalData.res_lat = mylatitude1
                    app.globalData.res_long = mylongitude1
                  } else if (res.cancel) {
                  }
                }
              })
            } else if (res.cancel) {
              console.log('"Cancel" is tapped')
            }
          }
        })
      } else if (JSON.parse(res.data).param == "response") {
        console.log("It is res")
        wx.showToast({
          title: 'Your Friend ' + JSON.parse(res.data).user_name + 'went to help you',
          icon: 'none',
          duration: 5000
        })
      }else if(JSON.parse(res.data).param=="responseX"){
        wx.showToast({
          title: 'This user no longer needs help',
          icon: 'none',
          duration: 5000
        })
      }
    })
  },

  requestTextInput: function(e){
    this.setData({
      req_txt: e.detail.value
    })
  },
  changeToRed: function () {
    console.log("changeToRed");
    this.setData({
      red: "activ",
      yellow: "nonactiv",
      green: "nonactiv",
      req_lvl: 3
    })
    console.log(this.data.red + " " + this.data.yellow + " " + this.data.green);
  },

  changeToYellow: function () {
    console.log("changeToYellow");
    this.setData({
      red: "nonactiv",
      yellow: "activ",
      green: "nonactiv",
      req_lvl: 2
    })
    console.log(this.data.red + " " + this.data.yellow + " " + this.data.green);
  },

  changeToGreen: function () {
    console.log("changeToGreen");
    this.setData({
      red: "nonactiv",
      yellow: "nonactiv",
      green: "activ",
      req_lvl: 1
    })
    console.log(this.data.red+" "+this.data.yellow+" "+this.data.green);
  },
  
  sos:function(){
    const self = this;
    self.setData({
      counter: self.data.counter + 1
    }) 
    if(self.data.counter%2==0){
      //del req
      wx.request({
        url: 'http://' + app.globalData.server_url + '/DeleteRequest',
        method: "GET",
        header: {
          'content-type': 'application/json'
        },
        data:{
          user_id: app.globalData.myUserInfo.user_id,
          time: self.data.time
        },
        success(res){
          if(res.data=="Success"){
            wx.showToast({
              title: 'Your request is cancelled',
              duration: 4000,
              icon: 'none'
            })
          }else{
            wx.showToast({
              title: 'UnknowingError',
              duration: 4000,
              icon: 'fail'
            })
          }
        }
      })
    }else{
      self.setData({
        time: new Date().getTime()
      })
    wx.getLocation({
      success: function(res) {
        if(self.data.req_txt==''){
          self.setData({
            req_txt:'null'
          })
        }
        wx.sendSocketMessage({
          data:
            '{ param:req ,user_id:' + app.globalData.myUserInfo.user_id + ',txt:' + self.data.req_txt + ',lvl:' + self.data.req_lvl + ',mylatitude:' + res.latitude + ',mylongitude:' + res.longitude + ',time:' + self.data.time + ', private:'+self.data.private+'}'
        })
        wx.showToast({
          title: 'click again to cancel..',
          icon: 'none',
          duration: 5000
        })
      },
    })
    }
  },
  haversine_km: function (lat1, long1, lat2, long2) {
    console.log("lat1:" + lat1 + " long1:" + long1 + " lat2:" + lat2 + " long2:" + long2)
    const d2r = Math.PI / 180
    const dlong = (long2 - long1) * d2r
    const dlat = (lat2 - lat1) * d2r

    let a = Math.pow(Math.sin(dlat / 2.0), 2) + Math.cos(lat1 * d2r) * Math.cos(lat2 * d2r) * Math.pow(Math.sin(dlong / 2.0), 2)
    let c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a))
    let d = 6367 * c
    return (d/1000).toFixed(2)
  },
  getLoc: function(){
    self = this
    wx.getLocation({
      success: function (res) {
        self.setData({
          mylatitude: res.latitude,
          mylongitude: res.longitude
        })
      }
    })
  },
  privateChange: function(e){
    console.log(e.detail.value)
    this.setData({
      private: e.detail.value
    })
  }
})
