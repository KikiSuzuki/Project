// pages/login/login.js
const app = getApp()
Page({

  /**
   * Page initial data
   */
  data: {
    user_login:"",
    user_pwd:"",
    userInfo: {},
  },

  /**
   * Lifecycle function--Called when page load
   */
  onLoad: function (options) {
    wx.getUserInfo({
      success: res => {
        app.globalData.userInfo = res.userInfo
        this.setData({
          userInfo: res.userInfo,
        })
      }
    })
  },

  /**
   * Lifecycle function--Called when page is initially rendered
   */
  onReady: function () {

  },

  /**
   * Lifecycle function--Called when page show
   */
  onShow: function () {

  },

  userLoginInput:function(e){
    this.setData({
      user_login: e.detail.value
    })
  },
  userPwdInput: function (e) {
    console.log(e.detail.value);
    this.setData({
      user_pwd: e.detail.value
    })
  },
  toRegistration: function(){
    wx.navigateTo({
      url: '../registration/registration'
    })
  },

  toMain: function () {
    const self = this;
    if(self.data.user_login==''||self.data.user_pwd==''){
      wx.showToast({
        title: 'Login and Password field can not be empty',
        duration: 4000,
        icon: 'none'
      })
    }else{
      wx.request({
        url: 'http://' + app.globalData.server_url + '/Login',
        method: "GET",
        header: {
          'content-type': 'application/json'
        },
        data: {
          login: self.data.user_login,
          pwd: self.data.user_pwd,
          photopath: app.globalData.userInfo.avatarUrl
        },
        success(res) {
          console.log(res.data)
          if (res.data == 'User not found') {
            wx.showToast({
              title: 'User not found. Please check login and password',
              duration: 4000,
              icon: 'none'
            })
          } else {
            wx.connectSocket({
              url: 'ws://' + app.globalData.server_url + '/socket',
            })
            app.globalData.myUserInfo = res.data
            app.globalData.user_id = app.globalData.myUserInfo.user_id
            wx.switchTab({
              url: '../main/main'
            })
          }
        },
        fail(res) {
          wx.showToast({
            title: 'Error',
            duration: 4000,
            icon: 'none'
          })
        }
      })
    }
  },
  forgetPwd: function(e){
    wx.navigateTo({
      url: '../pwd/pwd'
    })
  }
})