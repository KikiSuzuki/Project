// pages/find/find.js
const app = getApp();
Page({

  /**
   * Page initial data
   */
  data: {
    users: [],
    nameORlogin: ""
  },
  findFriendInput: function (e) {
    console.log(app.globalData.user_id)
    const self = this;
    self.setData({
      nameORlogin: e.detail.value
    }),
      wx.request({
        url: 'http://' + app.globalData.server_url + '/FindFriend',
        method: "GET",
        header: {
          'content-type': 'application/json'
        },
        data: {
          loginORname: self.data.nameORlogin,
          user_id: app.globalData.myUserInfo.user_id
        },
        success(res) {
          console.log(res.data)
          self.setData({
            users: res.data
          })
        }
      })
  },
  addFriend: function (e) {
    console.log(e.target.dataset.user_id)
    wx.request({
      url: 'http://' + app.globalData.server_url + '/AddFriend',
      method: "GET",
      header: {
        'content-type': 'application/json'
      },
      data: {
        user1_id: app.globalData.myUserInfo.user_id,
        user2_id: e.target.dataset.user_id
      },
    })
  }
})