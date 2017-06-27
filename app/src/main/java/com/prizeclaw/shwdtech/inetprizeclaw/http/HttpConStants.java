package com.prizeclaw.shwdtech.inetprizeclaw.http;

/**
 * Created by bb on 16/9/11.
 */
public class HttpConStants {
    static String OPENYSHost = "https://open.ys7.com/api/lapp/token/get";
    static String HOST = "http://dust.shweidong.com/app";
    public static String TEST_HOST = "http://114.55.175.99:8090";

    public static final String TOKEN = TEST_HOST + "/token";

    // 获取首页地图数据
    public static final String MAP = TEST_HOST + "/api/Map";

    // 获取设备当前数据
    public static final String DEVICE_CURRENTDATA = TEST_HOST + "/api/DeviceCurrent";
    // 获取设备历史数据
    public static final String DEVICE_HISTORYDATA = TEST_HOST + "/api/HistoryData";
    // 获取区县统计信息
    public static final String DISTRICTAVG = TEST_HOST + "/api/DistrictAvg";
    // 获取指定区县下所有设备的统计信息
    public static final String DISTRICTDETAIL = TEST_HOST + "/api/DistrictDetail";
    // 获取
    public static final String CASCADEELEMENT = TEST_HOST + "/api/CascadeElement";
    // search
    public static final String SEARCH = TEST_HOST + "/api/Search";
    // StatisticsDetail
    public static final String STATISTICSDETAIL = TEST_HOST + "/api/StatisticsDetail";
    /**
     * 获取城市列表
     */
    public static final String CITY = HOST + "/City";
    /**
     * 主页饼图分布信息。
     * HTTPMethod: GET
     * {
     * projectType:1,   //int
     * good:10,    //int
     * normal:20,    //int
     * bad:25,   //int
     * }
     */
    public static final String SPREAD = HOST + "/Spread";

    /**
     * 获取所有工地的地图显示信息
     * HTTPMethod:POST 
     * PostData : { projectType:1 } 
     *
     * 获取区域工地的地图显示信息 
     * HTTPMethod: POST 
     * PostData:  { projectType:1, district:5 }
     *
     * 获取指定工地的地图显示信息 
     * HTTPMethod: POST 
     * PostData: { projectType:1, stat:5 }
     *
     * SERVER Response: 	[
     * {
     * id:5, //int
     * name:"XXX工地",//string
     * time:"2016-09-08 15:25:33",//string
     * tsp:0.665,//double         rate:0 //int
     * longitude:"121.521547",//string
     * latitude:"34.02154"//string
     * }
     */
    public static final String PROJECT_MAP = HOST + "/Map";

    /**
     * 获取各区县历史均值统计信息
     * 说明：进入区县历史详情页面时，显示区县历史详情信息。
     * POSTData:
     * [
     * {
     * projectType:1,
     * datatype:0  //0 = 小时均值， 1 = 日均值， 2 = 月均值
     * }
     * ]
     *
     * SERVER Response:
     * [
     * {
     * name:"黄浦区",//string
     * count:20,//int
     * tspAvg:0.665,//double
     * }
     * ]
     */
    public static final String DISTRICT_AVG = HOST + "/DistrictAvg";
    /**
     * 获取各区县均值统计信息  http:// 139.196.194.156:12698/api/DistrictCurrent
     * 	HTTPMethod: POST 
     * 	POSTData:  { projectType:1} 
     *
     * 	SERVER Response: [
     *  {
     * name:"黄浦区",//string
     * count:20,//int
     * tspAvg:0.665,//double
     * }
     */
    public static final String DISTRICT_CURRENT = HOST + "/DistrictCurrent";


    /**
     * 获取区县 相应项目的 当前信息
     * HTTPMethod: POST 
     * POSTData: [ {
     *  项目名称  projectType:1,
     *  区县id district:5} ]
     *
     * SERVER Response: [
     * { 
     * districtName:”黄浦区”//string
     * name:"XXX工地",//string
     * tsp:0.665,//double
     * }
     */
    public static final String DISTRICT_DETAIL = HOST + "/DistrictDetail";

    /**
     * 获取指定工地历史信息
     * 说明：从区域下属工地列表当前颗粒物详情页点击进入工地历史数据页面时使用，获取指定工地的历史数据。
     *       HTTPMethod: POST
     *       POSTData:[ {
     *           projectType:1,
     *           stat:5,
     *           type:0 //HistoryDataType
     *                 }]
     *
     *        SERVER Response:[{
     *                   stat:5,   //int
     *                   data:[{
     *                       date: “2016-09-07 10:00”,  //string
     *                       tsp:0.2365,   //double
     *                       rate : 0 //int
     *                        }]
     *                   }]
     */
    public static final String HISTORY_DATA  = HOST + "/HistoryData";


    /**
     * 更新网址
     */
    public static final String UPDATE_URL = HOST + "/Version?system=Android";
}
