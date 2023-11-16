package com.example.dddev.document;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/ground/{groundId}/general")
public class DocumentController {

	@PostMapping("/titles")
	public ResponseEntity<?> insertGeneralsWithTitles(@PathVariable("groundId") int groundId,
		@RequestBody GeneralInsertManyDto generalInsertManyDto,
		@RequestHeader String Authorization,
		HttpServletRequest request) {
		ResponseDto<List<GeneralResponseDto>> responseDto;
		ModelAndView modelAndView = (ModelAndView)request.getAttribute("modelAndView");
		User user = (User)modelAndView.getModel().get("user");
		UserDto userDto = user.convertToDto();

		List<GeneralResponseDto> generalList = generalService.insertGeneralsWithTitles(groundId, generalInsertManyDto,
			userDto);
		responseDto = ResponseDto.<List<GeneralResponseDto>>builder()
			.code(HttpStatus.OK.value())
			.message("일반 문서들이 생성되었습니다.")
			.data(generalList)
			.build();
		return new ResponseEntity<>(responseDto, HttpStatus.OK);

	}
}
