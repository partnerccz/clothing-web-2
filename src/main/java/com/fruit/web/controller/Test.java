package com.fruit.web.controller;

import java.io.*;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.*;

public class Test {
    private String testId;
    private String testName;
    String str = new String();
    public static void main(String[] args) {
        //sendMsg("13539403926", "65324");
        /*try {
            new Test().getProperties();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
//        queueDemo();
        /*stringBigDecimal("test");
        try {
            FileInputStream fis=new FileInputStream(new File(""));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }*/
        test();
    }

    public static void test() {
        System.out.println(12|5);
        System.out.println(12&5);
        System.out.println(12^5);
    }
    public static void doubleBigDecimal(){
        BigDecimal b1=new BigDecimal(0.5);
        BigDecimal b2=new BigDecimal(0.1);
        System.out.println(b1.add(b2));
    }
    // test
    /**
     *test
     */
    public static void stringBigDecimal(String test){
        BigDecimal b1=new BigDecimal("0.05");
        BigDecimal b2=new BigDecimal("0.01");
        System.out.println(b1.add(b2));
    }

    public static void doubleSure(){
        double b=3.0;
        double sum=30000;
        double i=0.1;
        double c=b+i;
        while(i<=0.3){
            sum=sum+c*360;
            System.out.println(sum);i++;
        }
        double a1=0.2;
        double a2=0.4;
        double a3=a1+a2;
        double b1=1.3;
        double b2=1.8;
        double b3=b1+b2;
        double c1=0.9;
        double c2=0.7;
        double d1=0.5;
        double d2=0.6;
        System.out.println(String.valueOf(a1+a2));
        System.out.println(a3);
        System.out.println(String.valueOf(a1+b1));
        System.out.println(String.valueOf(b1+b2));
        System.out.println(b3);
        System.out.println(a1+c1);
        System.out.println(a1+c2);
        System.out.println(a1+d1);
        System.out.println(a1+d2);
        System.out.println(1/2);
        System.out.println(1/3 *3);
        System.out.println(String.valueOf(0.1+0.2));

    }

    public static void queueDemo(){
        Queue<String> queue=new LinkedList<>();
        queue.offer("xiaoyun");
        queue.offer("yulon");
        queue.offer("与龙共舞");
        String element=null;
        while ((element=queue.poll())!=null){
            System.out.println(element+"\t");
        }
    }
    public static void arrayListDemo(){
        List<String> userlist = new ArrayList<String>();
        userlist.add("yulon");
        userlist.add("xiaoyun");
        userlist.add("羽龙共舞");
        System.out.println("使用普通for循环:");
        for(int i=0; i<userlist.size(); i++){
            System.out.print(userlist.get(i)+" ");
        }
        System.out.println();
        System.out.println();
        System.out.println("使用Iterator迭代器:");
        Iterator it = userlist.iterator();
        while(it.hasNext()){
            System.out.print(it.next()+" ");
        }
        System.out.println();
        System.out.println();
        System.out.println("使用增强for循环:");

        for(String s : userlist){
            System.out.print(s+" ");
        }
    }

    public static int saveToImgByStr(byte[] imgByte, String imgPath,
                                     String imgName) {
        int stateInt = 1;
        if (imgByte.length > 0) {
            try {
                File validateCodeFolder = new File(imgPath);
                if (!validateCodeFolder.exists()) {
                    validateCodeFolder.mkdirs();
                }
                // 将字符串转换成二进制，用于显示图片
                // 将上面生成的图片格式字符串 imgStr，还原成图片显示
                InputStream in = new ByteArrayInputStream(imgByte);
                File file = new File(imgPath, imgName);// 可以是任何图片格式.jpg,.png等
                FileOutputStream fos = new FileOutputStream(file);
                byte[] b = new byte[1024];
                int nRead = 0;
                while ((nRead = in.read(b)) != -1) {
                    fos.write(b, 0, nRead);
                }
                fos.flush();
                fos.close();
                in.close();

            } catch (Exception e) {
                stateInt = 0;
                e.printStackTrace();
            } finally {
            }
        }
        return stateInt;
    }
    /**
     * 发送POST方法的请求
     *
     * @return 所代表远程资源的响应结果
     */
    public static String sendMsg(String phone,String code)//phone手机号码,code验证码
    {
        String appKey = "060320392f36f84ba204639e7338e839";
        String appSecret = "db75b63e06f6";
        String nonce = "baoluo"; // 随机数（最大长度128个字符）
        String curTime = String.valueOf((new Date()).getTime() / 1000L); // 当前UTC时间戳
        System.out.println("curTime: " + curTime);


        String checkSum = CheckSumBuilder.getCheckSum(appSecret, nonce, curTime);
        System.out.println("checkSum: " + checkSum);

        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try
        {
            String url = "https://api.netease.im/sms/sendtemplate.action"; //网址可以不修改
            String encStr1 = URLEncoder.encode(code, "utf-8");
            String encStr2 = URLEncoder.encode("name", "utf-8"); // url编码；防止不识别中文
            String params = "templateid=3051232&mobiles=[\""+phone+"\"]"
                    + "&params=" + "[\"" + encStr1 + "\",\""+ encStr2 + "\"]";
            System.out.println("params：" + params);


            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("AppKey", appKey);
            conn.setRequestProperty("CheckSum", checkSum);
            conn.setRequestProperty("CurTime", curTime);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
            conn.setRequestProperty("Nonce", nonce);


            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());



            // 发送请求参数
            out.print(params);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            System.out.println(conn);
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            System.out.println("in"+in);
            String line;
            while ((line = in.readLine()) != null)
            {
                result += line;
            }
        } catch (Exception e)
        {
            System.out.println("发送 POST 请求出现异常！\n" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输出流、输入流
        finally
        {
            try
            {
                if (out != null)
                {
                    out.close();
                }
                if (in != null)
                {
                    in.close();
                }
            } catch (IOException ex)
            {
                ex.printStackTrace();
            }
        }
        return result;
    }


    private void getProperties() throws IOException {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("receiveMoney.properties");
        System.out.println("begin!!");
        Properties properties = new Properties();
        try{
            properties.load(inputStream);
        }catch (IOException ioE){
            ioE.printStackTrace();
        }finally{
            inputStream.close();
        }
        System.out.println("name:"+properties.getProperty("name"));
    }

    public static void useHashMapperAscOrDesc(){
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("c", 2);
        map.put("a", 10);
        map.put("b", 7);
        map.put("d", 6);
        hashMapperAscOrDesc(map);
    }

    public static void hashMapperAscOrDesc(Map map){
        List<Map.Entry<String,Integer>> list = new ArrayList<Map.Entry<String,Integer>>(map.entrySet());
        Collections.sort(list,new Comparator<Map.Entry<String,Integer>>() {
            //升序排序
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }

        });

        for(Map.Entry<String,Integer> mapping:list){
            System.out.println(mapping.getKey()+":"+mapping.getValue());
        }
    }

    public void useFormatString(){

        String provinceAndCity="";
        for (int i = 0; i < formatString().length; i++) {
            provinceAndCity+="{";
            for (int j = 0; j < formatString()[i].length; j++) {
                if(formatString()[i].length ==1){ //直辖市判断 为1时是直辖市
                    provinceAndCity+="'code':'"+i+"',";
                    provinceAndCity+="'showName':'"+formatString()[i][j]+"',";
                    provinceAndCity+="'city':''";
                }else{
                    if(j==0){ // 当j为1时是省份
                        provinceAndCity+="'code':'"+i+"',";
                        provinceAndCity+="'showName':'"+formatString()[i][j]+"',";
                        provinceAndCity+="'city':[";
                    }else{ // 否则是城市
                        provinceAndCity+="{'code':'";
                        provinceAndCity+=i+"-"+j+"',";
                        provinceAndCity+="'showName':'"+formatString()[i][j];
                        if(formatString()[i].length==j+1){
                            System.out.println("a");
                            provinceAndCity+="'}]";
                        }else{
                            provinceAndCity+="'},";
                        }
                    }
                }
            }
            if(formatString().length-1==i){
                provinceAndCity+="}";
            }else{
                provinceAndCity+="},";
            }
        }
        System.out.println(provinceAndCity);
    }

    public static String[][] formatString() {
        String[][] array = {
                new String[]{"北京"},
                new String[]{"上海"},
                new String[]{"天津"},
                new String[]{"重庆"},
                // 华北地区
                new String[]{"河北", "石家庄", "唐山", "秦皇岛", "邯郸", "邢台", "保定", "张家口", "承德", "沧州", "廊坊",
                        "衡水"},
                new String[]{"山西", "太原", "大同", "阳泉", "长治", "晋城", "朔州", "晋中", "运城", "忻州", "临汾",
                        "吕梁"},
                new String[]{"内蒙古自治区", "呼和浩特", "包头", "乌海", "赤峰", "通辽", "鄂尔多斯", "呼伦贝尔", "巴彦淖尔",
                        "乌兰察布", "兴安", "锡林郭勒", "阿拉善"},
                // 东北地区
                new String[]{"辽宁", "沈阳", "大连", "鞍山", "抚顺", "本溪", "丹东", "锦州", "营口", "阜新", "辽阳",
                        "盘锦", "铁岭", "朝阳", "葫芦岛"},
                new String[]{"吉林", "长春", "吉林", "四平", "辽源", "通化", "白山", "松原", "白城", "延边"},
                new String[]{"黑龙江", "哈尔滨", "齐齐哈尔", "鸡西", "鹤岗", "双鸭山", "大庆", "伊春", "佳木斯", "七台河",
                        "牡丹江", "黑河", "绥化", "大兴安岭"},
                // 华东地区
                new String[]{"江苏", "南京", "无锡", "徐州", "常州", "苏州", "南通", "连云港", "淮安", "盐城", "扬州",
                        "镇江", "泰州", "宿迁"},
                new String[]{"浙江", "杭州", "宁波", "温州", "嘉兴", "湖州", "绍兴", "金华", "衢州", "舟山", "台州",
                        "丽水"},
                new String[]{"安徽", "合肥", "芜湖", "蚌埠", "淮南", "马鞍山", "淮北", "铜陵", "安庆", "黄山", "滁州",
                        "阜阳", "宿州", "巢湖", "六安", "亳州", "池州", "宣城"},
                new String[]{"福建", "福州", "厦门", "莆田", "三明", "泉州", "漳州", "南平", "龙岩", "宁德"},
                new String[]{"江西", "南昌", "景德镇", "萍乡", "九江", "新余", "鹰潭", "赣州", "吉安", "宜春", "抚州",
                        "上饶"},
                new String[]{"山东", "济南", "青岛", "淄博", "枣庄", "东营", "烟台", "潍坊", "威海", "济宁", "泰安",
                        "日照", "莱芜", "临沂", "德州", "聊城", "滨州", "菏泽"},
                // 中南地区
                new String[]{"河南", "郑州", "开封", "洛阳", "平顶山", "焦作", "鹤壁", "新乡", "安阳", "濮阳", "许昌",
                        "漯河", "三门峡", "南阳", "商丘", "信阳", "周口", "驻马店"},
                new String[]{"湖北", "武汉", "黄石", "襄樊", "十堰", "荆州", "宜昌", "荆门", "鄂州", "孝感", "咸宁",
                        "随州", "恩施"},
                new String[]{"湖南", "长沙", "株洲", "湘潭", "衡阳", "邵阳", "岳阳", "常德", "张家界", "益阳", "郴州",
                        "永州", "怀化", "娄底", "湘西"},
                new String[]{"广东", "广州", "深圳", "珠海", "汕头", "韶关", "佛山", "江门", "湛江", "茂名", "肇庆",
                        "惠州", "梅州", "汕尾", "河源", "阳江", "清远", "东莞", "中山", "潮州", "揭阳",
                        "云浮"},
                new String[]{"广西自治区", "南宁", "柳州", "桂林", "梧州", "北海", "防城港", "钦州", "贵港", "玉林", "百色",
                        "贺州", "河池", "来宾", "崇左"},
                new String[]{"海南", "海口", "三亚"},
                // 西南地区
                new String[]{"四川", "成都", "自贡", "攀枝花", "泸州", "德阳", "绵阳", "广元", "遂宁", "内江", "乐山",
                        "南充", "宜宾", "广安", "达州", "眉山", "雅安", "巴中", "资阳", "阿坝", "甘孜",
                        "凉山"},
                new String[]{"贵州", "贵阳", "六盘水", "遵义", "安顺", "铜仁", "毕节", "黔西南", "黔东南", "黔南"},
                new String[]{"云南", "昆明", "曲靖", "玉溪", "保山", "昭通", "丽江", "普洱", "临沧", "文山", "红河",
                        "西双版纳", "楚雄", "大理", "德宏", "怒江", "迪庆"},
                new String[]{"西藏自治区", "拉萨", "昌都", "山南", "日喀则", "那曲", "阿里", "林芝"},
                // 西北地区
                new String[]{"陕西", "西安", "铜川", "宝鸡", "咸阳", "渭南", "延安", "汉中", "榆林", "安康", "商洛"},
                new String[]{"甘肃", "兰州", "嘉峪关", "金昌", "白银", "天水", "武威", "张掖", "平凉", "酒泉", "庆阳",
                        "定西", "陇南", "临夏", "甘南"},
                new String[]{"青海", "西宁", "海东", "海北", "黄南", "海南", "果洛", "玉树", "海西"},
                new String[]{"宁夏自治区", "银川", "石嘴山", "吴忠", "固原", "中卫"},
                new String[]{"新疆自治区", "乌鲁木齐", "克拉玛依", "吐鲁番", "哈密", "和田", "阿克苏", "喀什", "克孜勒苏柯尔克孜",
                        "巴音郭楞蒙古", "昌吉", "博尔塔拉蒙古", "伊犁哈萨克", "塔城", "阿勒泰"},
                // 港澳台
                new String[]{"香港特别行政区"}, new String[]{"澳门特别行政区"},
                new String[]{"台湾", "台北", "高雄", "基隆", "台中", "台南", "新竹", "嘉义"}
        };
        return  array;
    }
}
class CheckSumBuilder {
    // 计算并获取CheckSum
    public static String getCheckSum(String appSecret, String nonce, String curTime) {
        return encode("sha1", appSecret + nonce + curTime);
    }


    // 计算并获取md5值
    public static String getMD5(String requestBody) {
        return encode("md5", requestBody);
    }


    private static String encode(String algorithm, String value) {
        if (value == null) {
            return null;
        }
        try {
            MessageDigest messageDigest
                    = MessageDigest.getInstance(algorithm);
            messageDigest.update(value.getBytes());
            return getFormattedText(messageDigest.digest());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    private static String getFormattedText(byte[] bytes) {
        int len = bytes.length;
        StringBuilder buf = new StringBuilder(len * 2);
        for (int j = 0; j < len; j++) {
            buf.append(HEX_DIGITS[(bytes[j] >> 4) & 0x0f]);
            buf.append(HEX_DIGITS[bytes[j] & 0x0f]);
        }
        return buf.toString();
    }
    private static final char[] HEX_DIGITS = { '0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
}