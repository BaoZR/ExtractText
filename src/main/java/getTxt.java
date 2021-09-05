import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class getTxt {
    /**
     * 获取日志文件中的时间
     *
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws IOException {
        {

            File f=new File("out.txt");
            f.createNewFile();
            FileOutputStream fileOutputStream = new FileOutputStream(f);
            PrintStream printStream = new PrintStream(fileOutputStream);
            System.setOut(printStream);


            //匹配次数
            int matchTime = 0;
            //存匹配上的字符串
            List<String> strs = new ArrayList<>();
            try {
                //编码格式
                String encoding = "UTF-8";
                //文件路径
                File file = new File("D:\\temp\\temp.txt");
                if (file.isFile() && file.exists()) { // 判断文件是否存在
                    //输入流
                    InputStreamReader read = new InputStreamReader(
                            new FileInputStream(file), encoding);// 考虑到编码格
                    BufferedReader bufferedReader = new BufferedReader(read);
                    String lineTxt = null;
                    //读取一行
                    while ((lineTxt = bufferedReader.readLine()) != null) {
                        if (lineTxt.startsWith("light_0")) {
                            deallist(strs);
                            strs.clear();
                        }
                        strs.add(lineTxt);

                        //正则表达式
                        //matchTime = getMatchTime(matchTime, strs, lineTxt);
                    }
                    deallist(strs);
                    read.close();
                } else {
                    System.out.println("找不到指定的文件");
                }
            } catch (Exception e) {
                System.out.println("读取文件内容出错");
                e.printStackTrace();
            }
//            List<Integer> nums = getSum(strs);
//            double avg = getAvgTime(nums,matchTime);
            //           System.out.print(avg);
        }
    }

    private static int getMatchTime(int matchTime, List<String> strs, String lineTxt) {
        Pattern p = Pattern.compile("[0-9]*ms$");
        Matcher m = p.matcher(lineTxt);
        boolean result = m.find();
        String find_result = null;
        if (result) {
            matchTime++;
            find_result = m.group(0);
            strs.add(find_result);
        }
        return matchTime;
    }

    private static void deallist(List<String> strs) {
        List<Integer> ints = new ArrayList<>();
        for (String str : strs) {
            Pattern p = Pattern.compile("light_\\d=(\\d+)");
            Matcher m = p.matcher(str);
            boolean result = m.find();

            if (result) {
                //System.out.println(m.group(1));
                ints.add(new Integer(m.group(1)));

            }
        }
        Integer best = null;
        Integer res = null;
        for (Integer i : ints) {
            if (res==null) {
                res = Math.abs(i - 130);
                best = i;
            }
            else{
                if (Math.abs(i - 130) < res) {
                    res = Math.abs(i - 130);
                    best = i;
                }
            }
        }
        System.out.println(best);


    }

}
