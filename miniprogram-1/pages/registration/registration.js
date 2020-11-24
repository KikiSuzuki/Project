// pages/registration/registration.js
const app = getApp()
const date = new Date()
const years = []
const months = []
const days = []

for (let i = 1900; i <= date.getFullYear(); i++) {
  years.push(i)
}

for (let i = 1; i <= 12; i++) {
  months.push(i)
}

for (let i = 1; i <= 31; i++) {
  days.push(i)
}

Page({

  /**
   * Page initial data
   */
  data: {
    user_photopath:"",
    user_login: "",
    user_name: "",
    user_gender: "1",
    user_question: "",
    user_answer: "",
    user_about: "",
    password1: "",
    password2: "",
    year: date.getFullYear(),
    month: 2,
    day: 2,
    items: [
      { name: 'Female', value: '2' },
      { name: 'Male', value: '1', checked: 'true' },
    ],
    years: years,
    months: months,
    days: days,
    value: [9999, 1, 1],
  },

  /**
   * Lifecycle function--Called when page load
   */
  onLoad: function (options) {
    this.setData({
      user_name: app.globalData.userInfo.nickName,
      user_photopath: app.globalData.userInfo.avatarUrl
    })
    if (app.globalData.userInfo.gender != '0') {
      this.setData({
        user_gender: app.globalData.userInfo.gender
      })
    }
  },

  userLoginInput: function (e) {
    console.log(e.detail);
    this.setData({
      user_login: e.detail.value
    })
  },
  userNameInput: function (e) {
    console.log(e.detail);
    this.setData({
      user_name: e.detail.value
    })
  },
  genderChange: function (e) {
    this.setData({
      user_gender: e.detail.value
    })
  },
  dobChange: function (e) {
    const val = e.detail.value
    this.setData({
      year: this.data.years[val[0]],
      month: this.data.months[val[1]],
      day: this.data.days[val[2]]
    })
  },
  userQuestionInput: function (e) {
    console.log(e.detail);
    this.setData({
      user_question: e.detail.value
    })
  },
  userAnswerInput: function (e) {
    console.log(e.detail);
    this.setData({
      user_answer: e.detail.value
    })
  },
  userPassword1Input: function (e) {
    console.log(e.detail);
    this.setData({
      password1: e.detail.value
    })
  },
  userPassword2Input: function (e) {
    console.log(e.detail);
    this.setData({
      password2: e.detail.value
    })
  },
  userAboutInput: function (e) {
    this.setData({
      user_about: e.detail.value
    })
  },

  saveNewUser: function (options){
    const self = this;
    if (self.data.password1 == self.data.password2 && self.data.password1!=''){
      if(self.data.user_login==''&&self.data.user_name!=''&&self.data.user_answer==''&&self.data.user_question==''){
        wx.showToast({
          title: 'Login, Name, Answer and Question fiend can not be empty',
          duration: 5000,
          icon: 'none'
        })
      }else{
        wx.request({
          url: 'http://' + app.globalData.server_url + '/Registration',
          method: "GET",
          header: {
            'content-type': 'application/json'
          },
          data: {
            login: self.data.user_login,
            name: self.data.user_name,
            gender: self.data.user_gender,
            dob: self.data.day + "-" + self.data.month + "-" + self.data.year,
            question: self.data.user_question,
            answer: self.data.user_answer,
            pwd: self.data.password1,
            about: self.data.user_about,
            photo: self.data.user_photopath
          },
          success(res) {
            if (res.data == "Success") {
              wx.reLaunch({
                url: '../login/login',
              })
              wx.showToast({
                title: 'Success',
                duration: 4000,
                icon: 'success'
              })
            } else if (res.data=='This login is already exists'){
              wx.showToast({
                title: 'This login is already exists',
                duration: 4000,
                icon: 'none'
              })
            }else{
              wx.showToast({
                title: 'Error',
                duration: 4000,
                icon: 'fail'
              })
            }
          }
        })
      }
    }else{
      wx.showToast({
        title: 'You need to write the same password for confirming',
        duration: 4000,
        icon: 'none'
      })
    }
  },
  
})