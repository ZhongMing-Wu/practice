package com.plateform.util;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.Version;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Map;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class createDocx {

    /**
     * 生成word
     * @param basePath word所在的文件夹
     * @m wordInZipName word改为zip后word的名字
     * @param dataMap word里面需要替换的参数
     */
    public  static void makeWord(String basePath, String filename, HttpServletResponse response
                                 , Map<String,Object> dataMap) {
        String wordInZipName = filename + ".zip";
        /** 指定输出word文件的路径 **/
        String outFilePath = basePath + File.separator+ UUID.randomUUID().toString() + ".xml";
        File docXmlFile = new File(outFilePath);

        try {
            /** 初始化配置文件 **/
            Configuration configuration = new Configuration(new Version("2.3.29"));
            configuration.setDefaultEncoding("utf-8");
            /** 加载文件 **/
            configuration.setDirectoryForTemplateLoading(new File(basePath));
            /** 加载模板 **/
            Template template = configuration.getTemplate(filename+".xml");

            /**数据渲染到word**/
            FileOutputStream fos = new FileOutputStream(docXmlFile);
            OutputStreamWriter oWriter = new OutputStreamWriter(fos, "UTF-8");
            Writer out = new BufferedWriter(oWriter, 10240);
            template.process(dataMap, out);
            out.close();
            fos.close();

            /**读取压缩文件**/
            ZipInputStream zipInputStream = wrapZipInputStream(new FileInputStream(new File(basePath + File.separator+wordInZipName)));
            /**压缩文件写入到目标路径**/
            /*File wordOutFile =  new File("D:\\testSp" + File.separator + wordOutName);
            wordOutFile.createNewFile();*/

            ServletOutputStream servletOutputStream = response.getOutputStream();
            BufferedOutputStream bufferedOutPut = new BufferedOutputStream(servletOutputStream);
            bufferedOutPut.flush();
            ZipOutputStream zipOutputStream = wrapZipOutputStream(bufferedOutPut);
            String itemName = "word/document.xml";
            /**替换参数**/
            replaceItem(zipInputStream, zipOutputStream, itemName, new FileInputStream(docXmlFile));

        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        } finally {
            docXmlFile.delete();
        }
    }

    /**
     * 替换某个 item,
     * @param zipInputStream zip文件的zip输入流
     * @param zipOutputStream 输出的zip输出流
     * @param itemName 要替换的 item 名称
     * @param itemInputStream 要替换的 item 的内容输入流
     */
    public static void replaceItem(
            ZipInputStream zipInputStream,
            ZipOutputStream zipOutputStream,
            String itemName,
            InputStream itemInputStream){
        if(null == zipInputStream){return;}
        if(null == zipOutputStream){return;}
        if(null == itemName){return;}
        if(null == itemInputStream){return;}
        ZipEntry entryIn;
        try {
            while((entryIn = zipInputStream.getNextEntry())!=null)
            {
                String entryName =  entryIn.getName();
                ZipEntry entryOut = new ZipEntry(entryName);
                // 只使用 name
                zipOutputStream.putNextEntry(entryOut);
                // 缓冲区
                byte [] buf = new byte[8*1024];
                int len;

                if(entryName.equals(itemName)){
                    // 使用替换流
                    while((len = (itemInputStream.read(buf))) > 0) {
                        zipOutputStream.write(buf, 0, len);
                    }
                } else {
                    // 输出普通Zip流
                    while((len = (zipInputStream.read(buf))) > 0) {
                        zipOutputStream.write(buf, 0, len);
                    }
                }
                // 关闭此 entry
                zipOutputStream.closeEntry();

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //e.printStackTrace();
            close(itemInputStream);
            close(zipInputStream);
            close(zipOutputStream);
        }
    }

    /**
     * 包装输入流
     */
    public static ZipInputStream wrapZipInputStream(InputStream inputStream){
        ZipInputStream zipInputStream = new ZipInputStream(inputStream);
        return zipInputStream;
    }

    /**
     * 包装输出流
     */
    public static ZipOutputStream wrapZipOutputStream(OutputStream outputStream){
        ZipOutputStream zipOutputStream = new ZipOutputStream(outputStream);
        return zipOutputStream;
    }
    private static void close(InputStream inputStream){
        if (null != inputStream){
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void close(OutputStream outputStream){
        if (null != outputStream){
            try {
                outputStream.flush();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
