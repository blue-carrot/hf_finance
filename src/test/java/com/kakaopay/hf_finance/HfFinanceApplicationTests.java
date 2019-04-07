package com.kakaopay.hf_finance;

import com.kakaopay.hf_finance.controller.FileUploadController;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.nio.charset.Charset;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HfFinanceApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;

    @Test
    public void testController() throws Exception {
        ResultMatcher ok = MockMvcResultMatchers.status().isOk();

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("2019경력공채_서버개발_사전과제3_주택금융신용보증_금융기관별_공급현황.csv").getFile());

//        String fileName = "test.txt";
//        File file = new File(FileUploadController. fileName);
//        //delete if exits
//        file.delete();

        MultipartFile multipartFile = new MockMultipartFile(fileFullPath, fileName, null, new FileInputStream(file));

        MockMultipartFile mockMultipartFile = new MockMultipartFile("user-file",file.getName(),
                "text/plain", "test data".getBytes());

        MockHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.fileUpload("/app/upload")
                        .file(mockMultipartFile);
        this.mockMvc.perform(builder).andExpect(ok)
                .andDo(MockMvcResultHandlers.print());;
        Assert.assertTrue(file.exists());
    }

//    public void imporTest() {
//        String fileDir = "/tmp";
//        String fileName = "import_target.xls";
//        String fileFullPath = String.format("%s/%s", fileDir, fileName);
//
//        MultipartFile multipartFile = new MockMultipartFile(fileFullPath, fileName, null, new FileInputStream(file));
//        // Do any works.
//        // testSo.parseXLS(multipartFile);
//    }

}
