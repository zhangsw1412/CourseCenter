package buaa.course.utils;

/**
 * Created by 熊纪元 on 2016/7/8.
 */
public class FileNameUtil{
    public static String getFileName(String fileUrl){
        return fileUrl.substring(fileUrl.lastIndexOf("/")+1);
    }
}
