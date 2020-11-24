// pages/friends/friends.js
const app = getApp();
Page({

  /**
   * Page initial data
   */
  data: {
    friends:[],
    new_friends:[]
  },

  /**
   * Lifecycle function--Called when page load
   */
  onLoad: function (options) {
  },
  onShow: function(options){
    this.findMyFriends();
    this.findNewFriendReq();
  },

  findMyFriends:function(){
    const self = this
    wx.request({
      url: 'http://' + app.globalData.server_url + '/MyFriends',
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
          friends: res.data
        })

      }
    })
  },
  findNewFriendReq: function(){
    const self=this;
    wx.request({
      url: 'http://' + app.globalData.server_url + '/NewFriendsReq',
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
          new_friends: res.data
        })
      }
    })
  },
 deleteFriend: function(e){
   console.log(e.target.dataset.user_id)
    wx.showModal({
      title: 'Delete friend confirm',
      content: 'Do you really want to delete' + e.target.dataset.user_name + 'from friends',
      cancelText: 'No',
      confirmText:'Yes',
      success(res){
        if(res.confirm){
        wx.request({
          url: 'http://' + app.globalData.server_url + '/DeleteFriend',
          method: "GET",
          header: {
            'content-type': 'application/json'
          },
          data: {
            user1_id: e.target.dataset.user_id,
            user2_id: app.globalData.myUserInfo.user_id
          },
        })
      }
    }
  })
 },
  confirmFriend:function(e){
    console.log(e.target.dataset.user_id)
    wx.request({
      url: 'http://' + app.globalData.server_url + '/ConfirmFriend',
      method: "GET",
      header: {
        'content-type': 'application/json'
      },
      data: {
        user1_id: e.target.dataset.user_id,
        user2_id: app.globalData.myUserInfo.user_id
      },
    })
  },
  refuseFriend:function(e){
    this.deleteFriend(e);
  }
})

