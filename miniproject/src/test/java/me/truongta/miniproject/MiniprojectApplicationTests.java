package me.truongta.miniproject;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;

import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import me.truongta.dao.RoleRepository;
import me.truongta.dao.UserRepository;
import me.truongta.entity.Role;
import me.truongta.entity.Users;
import me.truongta.restcontroller.UsersController;
import me.truongta.security.JwtService;
import me.truongta.security.UserService;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = UsersController.class)
class MiniprojectApplicationTests {

	@Autowired
	private MockMvc mockMvc;
	@MockBean
	UserRepository udao;

	@MockBean
	RoleRepository rdao;

	@MockBean
	ServletContext app;
	@MockBean
	UserService userService;
	@MockBean
	JwtService jwtService;

	@Before
	public void setUp() {
		List<Users> list = Arrays
				.asList(new Users("admin", "123", "truong ta", new Date(), "ta", "ta", true, new Role()));
		Mockito.when(udao.findAll()).thenReturn(list);

	}

	@Test
	void contextLoads() throws Exception {
		List<Users> list = Arrays
				.asList(new Users("admin", "123", "truong ta", new Date(), "ta", "ta", true, new Role()));
		given(udao.findAll()).willReturn(list);
		 mockMvc.perform(get("/api/v1/users?pageNumber=2&size=2").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

		
	}

}
