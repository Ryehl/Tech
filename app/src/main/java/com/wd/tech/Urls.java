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
    public static final String FindUser_Url="community/verify/v1/findUserPostById";

    //根据手机号进行查询 get phone
    public static final String QueryFriendByPhone = "user/verify/v1/findUserByPhone";

    //查询好友信息 get friendUid
    public static final String QueryFriendById = "user/verify/v1/queryFriendInformation";

    //检查是不是我的好友 get friendUid flag 1=好友 2=x
    public static final String CheckMyFriend = "chat/verify/v1/checkMyFriend";
}
