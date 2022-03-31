package net.developia.spring03;

import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.extern.log4j.Log4j;
import net.developia.spring03.dto.BoardDTO;

@Log4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:**/*-context.xml"}) //servlet-context.xml
public class MyTest {
	
	
	@BeforeClass //모든 메소드 실행 전 최초 1번 실행
	public static void testBeforeClass() {
		log.info("@BeforeClass: 테스트 시 최초 한번 실행");
	}
	@AfterClass //모든 메소드 실행 전 최초 1번 실행
	public static void testAfterClass() {
		log.info("@AfterClass: 테스트 시 최초 한번 실행");
	}

	
	 //각 테스트 전에 실행
	public void testBefore() {
		log.info("@Before: 각 메소드가 실행되기 전 실행");
	}
	
	@After //각 테스트 후에 실행
	public void testAfter() {
		log.info("@After: 각 메소드가 실행된 후 실행");
	}
	

	@Test
	public void testBorardDTO() {
		BoardDTO boardDTO = new BoardDTO();
		boardDTO.setNo(1);
		boardDTO.setTitle("제목");
		boardDTO.setName("이름");
		boardDTO.setContent("내용");
		boardDTO.setPassword("1234");
		
		log.info(boardDTO.toString());
		assertNotNull(boardDTO);
	}
	
	public void testTimeout() {
		BoardDTO boardDTO = new BoardDTO();
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		boardDTO.setNo(1);
		boardDTO.setTitle("제목");
		boardDTO.setName("이름");
		boardDTO.setPassword("1111");
		log.info(boardDTO.toString());
		assertNotNull(boardDTO);
	}
	
	@Test
	@Ignore
	public void testIgnore() {
		log.info("실행하지 않음");
	}
	
}
