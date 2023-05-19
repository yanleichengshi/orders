package com.celiaKey.orders.mvc.service.impl;

import cn.hutool.extra.qrcode.QrCodeUtil;
import com.celiaKey.orders.config.CommonCons;
import com.celiaKey.orders.mvc.dao.BannerDao;
import com.celiaKey.orders.mvc.entity.Banner;
import com.celiaKey.orders.mvc.service.FileService;
import com.celiaKey.orders.utils.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Slf4j
@Service
public class FileServiceImpl implements FileService {
    @Resource
    private BannerDao bannerDao;

    @Override
    public void upload(MultipartFile file) {
        fileToService(file);
        dataToDb(file);
    }

    private void fileToService(MultipartFile file) {
        log.info("start copy file to service");
        String path = "E:\\work_relate\\upload";
        String fileName = file.getOriginalFilename();
        File newFile = new File(path + File.separator + fileName);
        try {
            newFile.createNewFile();
            OutputStream out = new FileOutputStream(newFile);
            StreamUtils.copy(file.getInputStream(), out);
            out.close();
            log.info("copy of");
        } catch (IOException e) {
            e.printStackTrace();
            log.error("copy error, {}", e.getMessage());
        }
    }

    private void dataToDb(MultipartFile file) {
        log.info("start insert data To Db");
        try {
            InputStream inputStream = file.getInputStream();
            XSSFWorkbook xssfSheets = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = xssfSheets.getSheetAt(0);
            XSSFRow row = null;
            for (int i = 0; i < sheet.getLastRowNum(); i++) {
                row = sheet.getRow(i);
                XSSFCell cell = row.getCell(0);
                String name = cell.getStringCellValue();
                System.out.println("name = " + name);
                cell = row.getCell(1);
                String sex = cell.getStringCellValue();
                System.out.println("sex = " + sex);
            }
            log.info("insert ok");
        } catch (IOException e) {
            e.printStackTrace();
            log.error("insert error, {}", e.getMessage());
        }
    }

    @Override
    public void downloadExcel(HttpServletResponse response) {
        List<Banner> banners = getBanners();
        if (CollectionUtils.isEmpty(banners)) return;

        //需要通过response给前端数据流，设置对应的response
        response.reset();
        response.setContentType("application/octet-stream; charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + "text.xlsx");

        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("banner");

        Row rowTitle = sheet.createRow(0);
        Cell cell1 = rowTitle.createCell(0);
        cell1.setCellValue("title");
        Cell cell2 = rowTitle.createCell(1);
        cell2.setCellValue("content");

        for (int i = 1; i < banners.size(); i++) {
            Row row = sheet.createRow(i);
            Cell cell11 = row.createCell(0);
            cell11.setCellValue(banners.get(i).getTitle());
            Cell cell22 = row.createCell(1);
            cell22.setCellValue(banners.get(i).getContent());
        }

        // 使用数据流返回给前端
        OutputStream out = null;
        try {
            out = response.getOutputStream();
            workbook.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private List<Banner> getBanners() {
        List<Banner> banners = bannerDao.queryAllBanner();
        if (CollectionUtils.isEmpty(banners)) {
            log.error("query fail");
            return Collections.EMPTY_LIST;
        }
        return banners;
    }

    @Override
    public void downloadTxt(HttpServletResponse response) {
        List<Banner> banners = getBanners();
        if (CollectionUtils.isEmpty(banners)) return;

        // 生成txt
        StringBuffer sb = new StringBuffer();
        banners.forEach(item -> sb
                .append(item.getTitle())
                .append("\t")
                .append(item.getContent())
                .append("\n"));
        File file = new File("E:\\work_relate\\upload\\one.txt");
        FileUtils.stringToFile(file, sb.toString());

        //需要通过response给前端数据流，设置对应的response
        response.reset();
        response.setContentType("application/octet-stream; charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + "text.txt");
        try {
            FileInputStream in = new FileInputStream(file);
            FileCopyUtils.copy(in, response.getOutputStream());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("ok");
    }

    @Override
    public void getQRCode(String content, HttpServletResponse response) {
        try {
            ServletOutputStream outputStream = response.getOutputStream();
            QrCodeUtil.generate(content, 120, 120, "png", outputStream);
            String fileName = URLEncoder.encode("QR.png", "UTF-8");
            response.setContentType("application/x-download");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            log.error("create QRCode fail, the reason is {}", e.getMessage());
        }
    }

    @Override
    public void getVerifyCode(HttpServletResponse response) {
        Random random = new Random();
        // 默认背景黑布
        BufferedImage image = new BufferedImage(200, 250, BufferedImage.TYPE_INT_BGR);
        // 获取画笔
        Graphics graphics = image.getGraphics();
        // 默认填充黑布为白色
        graphics.fillRect(0, 0, 200, 250);
        // 文字素材
        char[] chars = CommonCons.VERIFY_CODE.toCharArray();
        for (int i = 0; i <= 3; i++) {
            graphics.setColor(new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256)));
            graphics.setFont(new Font("微软雅黑", Font.BOLD, 30));
            char c = chars[random.nextInt(chars.length)];
            graphics.drawString(c + "", i * 20, 30);
        }
        // 画干扰线
        int max = random.nextInt(5);
        for (int i = 0; i < max; i++) {
            graphics.setColor(new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256)));
            graphics.drawLine(random.nextInt(100), random.nextInt(50), random.nextInt(100), random.nextInt(50));
        }
        // 画干扰点
        int max2 = random.nextInt(5);
        for (int i = 0; i < max2; i++) {
            graphics.setColor(new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256)));
            graphics.drawLine(random.nextInt(80), random.nextInt(40), random.nextInt(5), random.nextInt(10));
        }
        // 输出验证码文件
        InputStream inputStream = null;
        OutputStream outputStream = null;
        File file = null;
        try {
            String path = this.getClass().getResource("/").getPath();
            file = new File(path + File.separator + "VerifyCode.png");
            ImageIO.write(image, "jpg", file);
            inputStream = new FileInputStream(file);
            outputStream = response.getOutputStream();
            byte[] bytes = new byte[1024];
            int len = 0;
            while ((len = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, len);
            }
            outputStream.flush();
            outputStream.close();
            inputStream.close();
            file.delete();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
