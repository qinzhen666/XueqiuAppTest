package ApiDemos.testcase;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xueqiu.app.testcase.TestSearch;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;

import javax.naming.Name;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestFileSource {

    public String name;
    public int age;

    @Test
    void readFile() throws IOException {
        //yaml中读取数据
        System.out.println(this.getClass().getResource("/"));
        System.out.println(this.getClass().getResource("/app/Test.yaml"));
        System.out.println(FileUtils.readFileToString(new File(this.getClass().getResource("/app/Test.yaml").getPath()),"utf8"));
        //打印类的信息，打印结果：ApiDemos.testcase.TestFileSource
        //把.换成/就可以作为类的数据文件的地址，便可以做类的数据驱动了,将文件的地址和类的地址保持一致即可
        System.out.println(this.getClass().getName());
        System.out.println(this.getClass().getCanonicalName());
    }

    @Test
    void writeJson() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File("demo.json"),this);
    }

    @Test
    void readJson() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        TestFileSource testFileSource = mapper.readValue(new File("demo.json"), this.getClass());
        System.out.println(testFileSource);
        System.out.println(testFileSource.age);
    }

    @Test
    void test(){
        String[] split = TestSearch.class.getCanonicalName().split("app");
        System.out.println(TestSearch.class.getCanonicalName());
        System.out.println("/com.xueqiu.app"+split[1].replace(".","/"));
        /*for (int i = 0; i < split.length; i++) {
            System.out.println(split[i]);
        }*/
//        String path1 = "/com.xueqiu.app" + Arrays.copyOfRange(split,3,split.length).toString() + ".yaml";
//        System.out.println(path1);
    }
}
