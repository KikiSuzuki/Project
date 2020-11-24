// pages/myPage/myPage.js
const app = getApp()

Page({
  data: {
    myUserInfo: {},
    items: [
      { name: 'Male', value: '1' },
      { name: 'Female', value: '2'},
    ],
    stat:{}
  },
  userNameInput: function (e) {
    console.log(e.detail);
    this.setData({
      "myUserInfo.user_name": e.detail.value
    })
  },
  genderChange: function (e) {
    this.setData({
      'myUserInfo.user_gender': e.detail.value
    })
  },
  userAboutInput: function (e) {
    this.setData({
      'myUserInfo.user_about': e.detail.value
    })
  },
  onShow:function(){
    this.setData({
      myUserInfo: app.globalData.myUserInfo,
    })
    const self = this;
    if (Number(self.data.items[0].value) === Number(self.data.myUserInfo.user_gender)){
      self.setData({
        'items[0].checked' : 'true',
      })
    }else{
      self.setData({
        'items[1].checked': 'true'
      })
    }
    wx.request({
      url: 'http://' + app.globalData.server_url + '/Statistics',
      method: "GET",
      data: {
        user_id: app.globalData.myUserInfo.user_id
      },
      header: {
        'content-type': 'application/json'
      },
      success(res) {
        console.log(res.data)
        self.setData({
          stat: res.data
        })
      }
    })
  },
  save:function(){
    const self = this
    wx.request({
      url: 'http://' + app.globalData.server_url + '/UpdateUserInfo',
      method: "GET",
      data: {
        user_id: app.globalData.myUserInfo.user_id,
        user_name: self.data.myUserInfo.user_name,
        user_gender: self.data.myUserInfo.user_gender,
        user_about: self.data.myUserInfo.user_about
      },
      header: {
        'content-type': 'application/json'
      },
      success(res) {
        console.log(res.data)
        app.globalData.myUserInfo = res.data
      }
    })
  },
  deleteUser:function(){
    const self = this;
    wx.showModal({
      title: 'Delete Account',
      content: 'You really want to delete this account?',
      confirmText:'Yes',
      cancelText: 'No',
      success(res){
        if(res.confirm){
          wx.request({
            url: 'http://' + app.globalData.server_url + '/DeleteUser',
            method: "GET",
            data: {
              user_id: app.globalData.myUserInfo.user_id,
            },
            header: {
              'content-type': 'application/json'
            },
            success(res){
              wx.reLaunch({
                url: '../login/login',
              })
            }
          })
        }
      }
    })
  }
})
