package com.board.jinsub.userClass;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.board.jinsub.bean.BFile;
import com.board.jinsub.bean.Reply;
import com.board.jinsub.dao.IBoardDao;

@Component
public class UploadTest {
	@Autowired
	private IBoardDao bDao;

	public String fileupTest(MultipartHttpServletRequest multi, int bnum) {
		// 1.이클립스의 물리적 저장경로 찾기
		String root = multi.getSession().getServletContext().getRealPath("/");
		System.out.println("root=" + root);
		String path = root + "resources/upload/";
		// 2.폴더 생성을 꼭 할것...
		File dir = new File(path);
		if (!dir.isDirectory()) { // upload폴더 없다면
			dir.mkdirs(); // upload폴더 생성
		}
		// 3.파일을 가져오기-파일태그 이름들 반환
		Iterator<String> files = multi.getFileNames(); // 파일업로드 2개이상일때

		Map<String, String> fMap = new HashMap<String, String>();
		fMap.put("bnum", String.valueOf(bnum));
		boolean f = false;
		while (files.hasNext()) {
			String fileTagName = files.next();
			System.out.println("fileTag=" + fileTagName);
			// 파일 메모리에 저장
			MultipartFile mf = multi.getFile(fileTagName); // 실제파일
			String oriFileName = mf.getOriginalFilename(); // a.txt
			fMap.put("oriFileName", oriFileName);
			// 4.시스템파일이름 생성 a.txt ==>112323242424.txt
			String sysFileName = System.currentTimeMillis() + "."
					+ oriFileName.substring(oriFileName.lastIndexOf(".") + 1);
			fMap.put("sysFileName", sysFileName);

			// 5.메모리->실제 파일 업로드

			try {
				mf.transferTo(new File(path + sysFileName));
			} catch (IOException e) {
				e.printStackTrace();
			}
			BFile bf = new BFile();
			bf.setBf_oriname(fMap.get("oriFileName"));
			bf.setBf_sysname(fMap.get("sysFileName"));
			System.out.println(fMap.get("oriFileName"));
			System.out.println(fMap.get("sysFileName"));
			// f=bDao.fileInsert(fMap.get("oriFileName"), fMap.get("sysFileName"), bnum);
			return fMap.get("oriFileName")+","+fMap.get("sysFileName");
		} // while End
		return "failed";
		/*if (f)
			return true;
		return false;*/
	}
}
