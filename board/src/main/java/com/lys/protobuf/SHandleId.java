package com.lys.protobuf;

public class SHandleId
{
	public static final int Dummy = 0;
	public static final int Test = 1;
	public static final int GetConfig = 30000; // 这个接口号不能轻易变，否则旧包启动不了
	public static final int GetOssToken = 30010;
	public static final int GetAppInfo = 30021; // 这个接口号不能轻易变，否则无法更新
	public static final int GetAppInfoList = 30022;
	public static final int SetAppInfo = 30023;
	public static final int UserPhoneCode = 30026;
	public static final int UserReg = 30027;
	public static final int UserLogin = 30011;
	public static final int GetUser = 30012;
	public static final int ModifyHead = 30013;
	public static final int ModifyName = 30019;
	public static final int ModifySex = 30020;
	public static final int ModifyGrade = 30024;
	public static final int ModifyPsw = 30025;
	public static final int GetUserList = 30014;
	public static final int AddUser = 30015;
	public static final int DeleteUser = 30016;
	public static final int SetVip = 30017;
	public static final int SetCp = 30018;
	public static final int GetFriendList = 30051;
	public static final int AddFriend = 30052;
	public static final int DeleteFriend = 30053;
	public static final int ModifyFriendGroup = 30054;
	public static final int FindTask = 30030;
	public static final int GetTask = 30031;
	public static final int GetTaskList = 30032;
	public static final int CreateTask = 30033;
	public static final int SendTask = 30034;
	public static final int DeleteTask = 30035;
	public static final int GetTaskFileVersion = 30036;
	public static final int GetTaskForWeb = 30037;
	public static final int SetTaskState = 30038;
	public static final int SetTaskNote = 30039;
	public static final int ModifyTask = 30040;
	public static final int ModifyTaskComment = 30041;
	public static final int AddTaskScore = 30042;
	public static final int SetTaskOpen = 30043;
	public static final int RandomOpenTask = 30044;
	public static final int FileDelete = 30091;
	public static final int FileList = 30092;
	public static final int FileExists = 30093;
	public static final int FileCopy = 30094;
	public static final int SearchTopics = 30100;
	public static final int GetTopicStyles = 30101;
	public static final int GetKnowledges = 30102;
	public static final int SvnGetDir = 30200;
	public static final int TopicRecordGetList = 30301;
	public static final int TopicRecordGet = 30302;
	public static final int TopicRecordSetFav = 30303;
	public static final int TopicRecordSetResult = 30304;
	public static final int TopicRecordDelete = 30305;
	public static final int TeachStart = 30401;
	public static final int TeachOverByTeacher = 30402;
	public static final int TeachQuestionByTeacher = 30403;
	public static final int TeachOverByStudent = 30404;
	public static final int TeachConfirmByStudent = 30405;
	public static final int TeachQuestionByStudent = 30406;
	public static final int TeachGetList = 30407;
	public static final int GetMatterList = 30600;
	public static final int AddModifyMatter = 30601;
	public static final int SwapMatter = 30602;
	public static final int DeleteMatter = 30603;
	public static final int GetBuyList = 30610;
	public static final int AddModifyBuy = 30611;
	public static final int DeleteBuy = 30612;
	public static final int GetCommentList = 30620;
	public static final int AddModifyComment = 30621;
	public static final int DeleteComment = 30622;
	public static final int GetGoodsList = 30700;
	public static final int AddModifyGoods = 30701;
	public static final int SwapGoods = 30702;
	public static final int DeleteGoods = 30703;
	public static final int GetOrderList = 30710;
	public static final int AddOrder = 30711;
	public static final int ModifyOrderState = 30712;
	public static final int GetTaskGroupList = 30720;
	public static final int AddModifyTaskGroup = 30721;
	public static final int SwapTaskGroup = 30722;
	public static final int DeleteTaskGroup = 30723;
	public static final int GetTeachList = 30730;
	public static final int ModifyTeach = 30731;
	public static final int AddEvent = 30800;
	public static final int GetEventList = 30801;
	public static final int LiveGetAll = 30901;
	public static final int LiveGetList = 30902;
	public static final int LiveAddModify = 30903;
	public static final int LiveDelete = 30904;
	public static final int LiveCopy = 30905;
	public static final int UserGroupGetAll = 30951;
	public static final int UserGroupAddModify = 30952;
	public static final int UserGroupDelete = 30953;
	public static final int ExamPointGetAll = 31001;
	public static final int ExamPointAddModify = 31002;
	public static final int ExamPointDelete = 31003;
	public static final int ExamPaperGetAll = 31101;
	public static final int ExamPaperAddModify = 31102;
	public static final int ExamPaperDelete = 31103;
	public static final int ExamPaperDetail = 31104;
	public static final int ExamRecordGetAll = 31201;
	public static final int ExamRecordAddModify = 31202;
	public static final int ExamRecordDelete = 31203;
	public static final int ExamRecordDetail = 31204;
	public static final int ZXCreateTask = 40011;
	public static final int ZXPullTask = 40012;
	public static final int ZXCatchPageOver = 40013;
	public static final int ZXCatchOver = 40014;
	public static final int ZXGenKnowledgeTree = 40015;
	public static final int ZXGenChapterTree = 40016;
	public static final int ZXAddTopic = 40017;
	public static final int ZXPullAccount = 40018;
	public static final int ZXReportAccount = 40019;
	public static final int ZXDeviceList = 40020;
	public static final int ZXTickInfo = 40021;
	public static final int ZXProcessJuan = 40022;
	public static final int ZXProcessJuan2 = 40023;
	public static final int GetServerLog = 50100;
	public static final int GetServerState = 50101;
	public static final int SetServerState = 50104;
	public static final int GetTimeRecord = 50103;
	public static final int GetServerUploadingList = 50102;

	public static String name(int value)
	{
		return ProtocolCommon.HandleId.valueOf(value).name().substring("HandleId_".length());
	}
}
