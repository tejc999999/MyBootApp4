package jp.te4a.spring.boot.mybootapp4;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import jp.te4a.spring.boot.myapp5.HelloController;

public class HelloControllerTest {

    private MockMvc mockMvc;

    @Before
    public void before() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(new HelloController()).build();
    }
	@Test
	public void index_正常系_HTTPステータス200返却() throws Exception {
        mockMvc.perform(get("/"))
        		.andExpect(status().isOk());
	}
	@Test
	public void index_正常系_モデルに属性msgが存在する() throws Exception {
        mockMvc.perform(get("/"))
        		.andExpect(status().isOk())
        		.andExpect(model().attributeExists("msg"));
	}
	@Test
	public void index_正常系_モデルの属性msgに規定文字列が格納される() throws Exception {
        mockMvc.perform(get("/"))
        		.andExpect(status().isOk())
        		.andExpect(model().attribute("msg", is("this is  a setting message")));
	}
	@Test
	public void index_正常系_viewがindexになる() throws Exception {
        mockMvc.perform(get("/"))
        		.andExpect(status().isOk())
        		.andExpect(view().name("index"));
	}
	
	@Test
	public void postForm_正常系_パラメタabcでHTTPステータス200返却() throws Exception {
        mockMvc.perform(post("/post").param("text1", "abc"))
        		.andExpect(status().isOk());
	}
	@Test
	public void postForm_正常系_パラメタabcでモデルに属性msgが存在する() throws Exception {
        mockMvc.perform(post("/post").param("text1", "abc"))
        		.andExpect(status().isOk())
        		.andExpect(model().attributeExists("msg"));
	}
	@Test
	public void postForm_正常系_パラメタabcでモデルの属性msgに規定文字列が格納される() throws Exception {
        mockMvc.perform(post("/post").param("text1", "abc"))
        		.andExpect(status().isOk())
        		.andExpect(model().attribute("msg", is("you write 'abc'!!!")));
	}
	@Test
	public void postForm_正常系_パラメタabcでviewがindexになる() throws Exception {
        mockMvc.perform(post("/post").param("text1", "abc"))
        		.andExpect(status().isOk())
        		.andExpect(view().name("index"));
	}
	
	@Test
	public void postForm_正常系_パラメタ空文字でHTTPステータス200返却() throws Exception {
        mockMvc.perform(post("/post").param("text1", ""))
        		.andExpect(status().isOk());
	}
	@Test
	public void postForm_正常系_パラメタ空文字でモデルに属性msgが存在する() throws Exception {
        mockMvc.perform(post("/post").param("text1", ""))
        		.andExpect(status().isOk())
        		.andExpect(model().attributeExists("msg"));
	}
	@Test
	public void postForm_正常系_パラメタ空文字でモデルの属性msgに規定文字列が格納される() throws Exception {
        mockMvc.perform(post("/post").param("text1", ""))
        		.andExpect(status().isOk())
        		.andExpect(model().attribute("msg", is("you write ''!!!")));
	}
	@Test
	public void postForm_正常系_パラメタ空文字でviewがindexになる() throws Exception {
        mockMvc.perform(post("/post").param("text1", ""))
        		.andExpect(status().isOk())
        		.andExpect(view().name("index"));
	}
	
	@Test
	public void postForm_異常系_パラメタなしでHTTPステータス400返却() throws Exception {
        mockMvc.perform(post("/post"))
        		.andExpect(status().isBadRequest());
	}
}
