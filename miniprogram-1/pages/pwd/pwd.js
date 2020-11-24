// pages/pwd/pwd.js
const app = getApp()
Page({

  data: {
    user:{},
    isQuestionTime:false,
    user_login:'',
    answer:'', 
    answerIsRight: false,
    pwd1:'',
    pwd2:''
  },

  loginInput:function(e){
    this.setData({
      user_login: e.detail.value
    })
  },
  answerInput: function(e) {
    this.setData({
      answer: e.detail.value
    })
  },
  userPassword1Input: function (e) {
    this.setData({
      pwd1: e.detail.value
    })
  },
  userPassword2Input: function (e) {
    this.setData({
      pwd2: e.detail.value
    })
  },
  checkAnswer:function(){
    if(this.data.answer==this.data.user.user_answer){
      this.setData({
        answerIsRight:true
      })
    }else{
      wx.showToast({
        title: 'Your answer is wrong, try again',
        icon:'none',
        duration:4000
      })
    }
  },
  findUser:function(){
    const self = this;
    wx.request({
      url: 'http://' + app.globalData.server_url + '/FindUser',
      method: "GET",
      data: {
        user_login: self.data.user_login
      },
      header: {
        'content-type': 'application/json'
      },
      success(res) {
        console.log(res.data)
        let x = res.data
        if (x==''){
          wx.showToast({
            title: 'User not found',
            icon: 'none',
            duration: 4000
          })
          self.setData({
            isQuestionTime: false
          })
        }else{
          self.setData({
            user:res.data,
            isQuestionTime: true
          })
        }
      }
    })
  },
  savePwd: function(){
    self = this;
    if(self.data.pwd1==self.data.pwd2){
      wx.request({
        url: 'http://' + app.globalData.server_url + '/ChangePwd',
        method: "GET",
        data: {
          user_login: self.data.user_login,
          user_pwd: self.data.pwd1
        },
        header: {
          'content-type': 'application/json'
        },
        success(res) {
          wx.navigateBack({
            url: '../login/login'
          })
          wx.showToast({
            title: 'Success',
            icon: 'success',
            duration: 4000
          })
        }
      })
    }else{
      wx.showToast({
        title: 'Confirm Password',
        icon: 'none',
        duration: 4000
      })
    }
  }
})