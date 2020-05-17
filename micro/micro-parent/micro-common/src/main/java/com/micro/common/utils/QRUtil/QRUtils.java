package com.micro.common.utils.QRUtil;

import com.google.gson.Gson;
import com.google.zxing.*;
import com.google.zxing.Result;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.micro.common.exception.ExceptionCode;
import okhttp3.*;
import org.apache.commons.lang3.StringUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class QRUtils {
    static int width = 300;
    static int height = 300;
    static String format = "png";  //二维码图片的格式
    //1.创建对应的MediaType
    private static final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");
    private static final OkHttpClient client = new OkHttpClient();

    public static void main(String[] args) throws Exception{
//        tests("");
//        File file = new File("D:/upload/033242A2.png");
//        uploadImage(file);
//        getRequests();
    }
    public static com.micro.common.utils.Result uploadImage(File file) throws Exception{

        //2.创建RequestBody
        RequestBody fileBody = RequestBody.create(MEDIA_TYPE_PNG, file);

        //3.构建MultipartBody
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file", "testImage.png", fileBody)
                .build();

        //4.构建请求
        Request request = new Request.Builder()//http://120.79.45.234/uploadLevelOne
                .url("http://120.79.45.234/uploadLevelOne")//http://192.168.2.172:8099/HomePage/goodShops
                .post(requestBody)
                .build();

        //5.发送请求
        Response response = client.newCall(request).execute();
        ResponseBody body = response.body();
        String string = body.string();
        if(StringUtils.isEmpty(string)){
            return  com.micro.common.utils.Result.failureResult(ExceptionCode.Failure.NOT_NULL);
//            return com.micro.common.utils.Result.failureResult()
        }
        Gson gson = new Gson();
//        ResultUtil result = gson.fromJson(string, ResultUtil.class);
//        System.out.println(string);
//        return result;
        return null;
    }
    private static void getRequests() {
        //1.创建OkHttpClient对象
        OkHttpClient okHttpClient = new OkHttpClient();
        File file = new File("D:/upload/033242A2.png");
        //2.2 创建 MediaType 设置上传文件类型
        MediaType MEDIATYPE = MediaType.parse("image/png");
        RequestBody requestBody = RequestBody.create(MEDIATYPE, file);
        //2.创建Request对象，设置一个url地址（百度地址）,设置请求方式。
        Request request = new Request.Builder().url("http://120.79.45.234/uploadLevelOne").method("POST",requestBody).build();
        //3.创建一个call对象,参数就是Request请求对象
        Call call = okHttpClient.newCall(request);
        //4.请求加入调度，重写回调方法
        call.enqueue(new Callback() {
            //请求失败执行的方法
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println(call);
            }
            //请求成功执行的方法
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String data = response.body().string();
                System.out.println(data);
            }
        });
    }
    private static void getRequest() {
        //1.创建OkHttpClient对象
        OkHttpClient okHttpClient = new OkHttpClient();
        //2.创建Request对象，设置一个url地址（百度地址）,设置请求方式。
        Request request = new Request.Builder().url("http://192.168.2.172:8099/HomePage/goodShops").method("GET",null).build();
        //3.创建一个call对象,参数就是Request请求对象
        Call call = okHttpClient.newCall(request);
        //4.请求加入调度，重写回调方法
        call.enqueue(new Callback() {
            //请求失败执行的方法
            @Override
            public void onFailure(Call call, IOException e) {
            }
            //请求成功执行的方法
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String data = response.body().string();
                System.out.println(data);
//                Log.d("response",data);
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        //更新UI
//                    }
//                });
            }
        });
    }

    //生成二维码
    public static com.micro.common.utils.Result generateQRCode(String text)  {
        Hashtable<EncodeHintType, Object> hints = new Hashtable<>();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        BitMatrix bitMatrix = null;
        String pathName = "c:/upload/"+text+".png";
        try {
            bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, width, height, hints);
            File outputFile = new File(pathName);
            if (!outputFile.exists()) {
                outputFile.mkdirs();
            }

            MatrixToImageWriter.writeToFile(bitMatrix, format, outputFile);
            com.micro.common.utils.Result result = uploadImage(outputFile);
            outputFile.delete();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return com.micro.common.utils.Result.failureResult(ExceptionCode.Failure.NOT_NULL);
    }
    //解析二维码
    public static String parseQRCode(String filePath) {
        String content = "";
        try {
            File file = new File(filePath);
            BufferedImage image = ImageIO.read(file);
            LuminanceSource source = new BufferedImageLuminanceSource(image);
            Binarizer binarizer = new HybridBinarizer(source);
            BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);
            Map<DecodeHintType, Object> hints = new HashMap<>();
            hints.put(DecodeHintType.CHARACTER_SET, "UTF-8");
            MultiFormatReader formatReader = new MultiFormatReader();
            Result result = formatReader.decode(binaryBitmap, hints);

            System.out.println("result 为：" + result.toString());
            System.out.println("resultFormat 为：" + result.getBarcodeFormat());
            System.out.println("resultText 为：" + result.getText());
            //设置返回值
            content = result.getText();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content;
    }

}

class MatrixToImageWriter {

    private static final int BLACK = 0xFF000000;
    private static final int WHITE = 0xFFFFFFFF;

    private MatrixToImageWriter() {}


    public static BufferedImage toBufferedImage(BitMatrix matrix) {
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE);
            }
        }
        return image;
    }

    //输出为文件
    public static void writeToFile(BitMatrix matrix, String format, File file)
            throws IOException {
        BufferedImage image = toBufferedImage(matrix);
        if (!ImageIO.write(image, format, file)) {
            throw new IOException("Could not write an image of format " + format + " to " + file);
        }
    }

    //输出为流
    public static void writeToStream(BitMatrix matrix, String format, OutputStream stream)
            throws IOException {
        BufferedImage image = toBufferedImage(matrix);
        if (!ImageIO.write(image, format, stream)) {
            throw new IOException("Could not write an image of format " + format);
        }
    }
}