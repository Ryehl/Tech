package com.wd.tech;

public class Urls {
    //等录
    public static final String Login_Ulr = "user/v1/login";

    //注册
    public static final String Register_Url = "user/v1/register";

    //加密
    public static final String Encrypt_Url = "tool/v1/encrypt";

    //轮播图XBunner
    public static final String XBanner_Url = "information/v1/bannerShow";

    //资讯消息列表
    public static final String Information_Url = "information/v1/infoRecommendList";

    //社区列表
    public static final String Community_Url = "community/v1/findCommunityList";

    //查询全量好友
    public static final String Friend_List = "chat/verify/v1/initFriendList";

    //我的收藏
    public static final String Collect_Url = "user/verify/v1/findAllInfoCollection";

    //我的关注
    public static final String Attention_Url = "user/verify/v1/findFollowUserList";

    //我的帖子
    public static final String Card_Url = "community/verify/v1/findMyPostById";

    //我的通知
    public static final String Notice_Url = "tool/verify/v1/findSysNoticeList";

    //我的积分
    public static final String Integral_Url = "user/verify/v1/findUserIntegral";

    //积分明细
    public static final String IntegralRecord_Url = "user/verify/v1/findUserIntegralRecord";

    //所有兴趣版块
    public static final String Interest_Url = "information/v1/findAllInfoPlate";

    //上传图片
    public static final String tz_dtsc = "community/verify/v1/releasePost";

    //详情页
    public static final String Details_Url = "information/v1/findInformationDetails";

    //详情页评论
    public static final String DetailsComment_Url = "information/v1/findAllInfoCommentList";

    //修改用户签名
    public static final String AlterSignature_Url = "user/verify/v1/modifySignature";

    //任务列表
    public static final String Task_Url = "user/verify/v1/findUserTaskList";

    //完善用户信息
    public static final String PersonalInformation_Url = "user/verify/v1/perfectUserInfo";

    //签到
    public static final String SignIn_Url = "user/verify/v1/userSign";

    //按文章搜索
    public static final String Article_Url = "information/v1/findInformationByTitle";

    //按作者搜索
    public static final String Author_Url = "information/v1/findInformationBySource";

    //取消收藏
    public static final String UnCollect_Url = "user/verify/v1/cancelCollection";

    //查询好友发布的帖子
    public static final String FindUser_Url = "community/verify/v1/findUserPostById";

    //根据手机号进行查询 get phone
    public static final String QueryFriendByPhone = "user/verify/v1/findUserByPhone";

    //查询好友信息 get friendUid
    public static final String QueryFriendById = "user/verify/v1/queryFriendInformation";

    //检查是不是我的好友 get friendUid flag 1=好友 2=x
    public static final String CheckMyFriend = "chat/verify/v1/checkMyFriend";

    //添加好友 post friendUid=int remark=String备注
    public static final String Add_Friend = "chat/verify/v1/addFriend";

    //删除好友 delete friendUid
    public static final String Delete_Friend = "chat/verify/v1/deleteFriendRelation";

    //查询群组信息
    public static final String Get_GroupInfo = "group/verify/v1/findGroupInfo";

    //检查我有没有在群里
    public static final String Check_IsInGroup = "group/verify/v1/whetherInGroup";

    //加入群聊
    public static final String JoinGroup = "group/verify/v1/applyAddGroup";

    //添加收藏(帖子)
    public static final String AddCollection_Url = "user/verify/v1/addCollection";

    //查询好友聊天记录
    public static final String QueryChatHis = "chat/verify/v1/findDialogueRecordPageList";

    //社区评论列表
    public static final String CommunityCommentList_Url = "community/v1/findCommunityUserCommentList";

    //在社区发发表评论
    public static final String AddCommunityComment_Url = "community/verify/v1/addCommunityComment";

    //根据极光用户名（可批量查询用户信息）
    public static final String FriendInfo_JUserName = "user/verify/v1/findConversationList";

    //微信登陆
    public static final String WeChatLogin_Url = "user/v1/weChatLogin";

    //微信绑定
    public static final String WeChatBinDing_Url = "user/verify/v1/bindWeChat";

    //删除好友 delete friendUid
    public static final String DeleteFriend = "chat/verify/v1/deleteFriendRelation";

    //根据手机号查询用户信息
    public static final String FindUserByPhone = "user/verify/v1/findUserByPhone";

    //查询好友申请通知
    public static final String Query_FriendNotice = "chat/verify/v1/findFriendNoticePageList";

    //审核好友申请
    public static final String Agree_Friendreq = "chat/verify/v1/reviewFriendApply";
}
