const app = getApp()
Page({
  data: {
    my_long:'',
    my_lat:'',
    markers: [{
      iconPath: "/resources/others.png",
      id: 0,
      latitude: 23.099994,
      longitude: 113.324520,
      width: 50,
      height: 50
    }]
  },
  onShow:function(){
    const self = this;
    if(app.globalData.my_lat!=''){
      self.setData({
        'markers[0].latitude' : app.globalData.res_lat,
        'markers[0].longitude': app.globalData.res_long,
      })
    }
    wx.getLocation({
      success: function(res) {
        self.setData({
          my_long:res.longitude,
          my_lat:res.latitude
        })
      },
    })
  }
})