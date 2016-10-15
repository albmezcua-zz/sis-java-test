//package com.sis.footballteams.util;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.json.JSONObject;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.context.MessageSource;
//import org.springframework.context.i18n.LocaleContextHolder;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.BindingResult;
//import org.springframework.validation.FieldError;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//
//import javax.servlet.http.HttpServletRequest;
//import java.io.IOException;
//import java.util.*;
//
//public class ResponseBuilder {
//
//    private static final Logger LOGGER = LoggerFactory.getLogger(ResponseBuilder.class);
//
//    private static final String SUCCESS = "success";
//    private static final String TOTAL = "totalCount";
//    //private static final String RESULT="result";
//    private static final String DATA = "data";
//    private static final String ERRORS = "errors";
//    private static final String MESSAGE = "message";
//
//    private static MessageSource messageSource;;
//
//
//    public static <T> ResponseEntity success(List<T> list) {
//
//        HashMap<String, Object> map = new HashMap<String, Object>(4);
//        map.put(TOTAL, list != null ? list.size() : 0);
//        map.put(SUCCESS, true);
//        map.put(DATA, list);
//        //map.put(MESSAGE, "");
//
//        String data = writeValueToMapper(map);
//
//        return (new ResponseEntity<String>(data, HttpStatus.OK));
//
//    }
//
//
//    public static <T> ResponseEntity successWithTotalCount(List<T> list, Integer totalCount) {
//
//        HashMap<String, Object> map = new HashMap<String, Object>(4);
//        map.put(TOTAL, list != null ? totalCount : 0);
//        map.put(SUCCESS, true);
//        map.put(DATA, list);
//        //map.put(MESSAGE, "");
//
//        String data = writeValueToMapper(map);
//
//        return (new ResponseEntity<String>(data, HttpStatus.OK));
//
//    }
//
//    public static <T> ResponseEntity success(List<T> list, int totalCount) {
//
//        HashMap<String, Object> map = new HashMap<String, Object>(4);
//
//        map.put(SUCCESS, true);
//        map.put(DATA, list);
//        // map.put(MESSAGE, "");
//        map.put(TOTAL, totalCount);
//
//        String data = writeValueToMapper(map);
//
//        return (new ResponseEntity<String>(data, HttpStatus.OK));
//
//    }
//
//
//    public static ResponseEntity success(Object entity) {
//
//        HashMap<String, Object> map = new HashMap<String, Object>(3);
//        map.put("success", true);
//
//        List<Object> list = new ArrayList<Object>(1);
//        list.add(entity);
//        map.put(SUCCESS, true);
//        map.put(DATA, list);
//        //  map.put(MESSAGE, "");
//        String data = writeValueToMapper(map);
//
//        return (new ResponseEntity<String>(data, HttpStatus.OK));
//
//    }
//
//    public static ValidationErrorDTO processFieldErrors(List<FieldError> fieldErrors) {
//        ValidationErrorDTO dto = new ValidationErrorDTO();
//
//        for (FieldError fieldError: fieldErrors) {
//            String localizedErrorMessage = resolveLocalizedErrorMessage(fieldError);
//            dto.addFieldError(fieldError.getField(), localizedErrorMessage);
//        }
//
//        return dto;
//    }
//
//    private static String resolveLocalizedErrorMessage(FieldError fieldError) {
//        Locale currentLocale =  LocaleContextHolder.getLocale();
//        String localizedErrorMessage = messageSource.getMessage(fieldError, currentLocale);
//
//        //If the message was not found, return the most accurate field error code instead.
//        //You can remove this check if you prefer to get the default error message.
//        if (localizedErrorMessage.equals(fieldError.getDefaultMessage())) {
//            String[] fieldErrorCodes = fieldError.getCodes();
//            localizedErrorMessage = fieldErrorCodes[0];
//        }
//
//        return localizedErrorMessage;
//    }
//
//    public ValidationErrorDTO validationError(MethodArgumentNotValidException e) {
//        LOGGER.error("Failed generate error response", e.getMessage());
//        BindingResult result = e.getBindingResult();
//        List<FieldError> fieldErrors = result.getFieldErrors();
//
//        return processFieldErrors(fieldErrors);
//
//        //return (new ResponseEntity<String>(data, HttpStatus.INTERNAL_SERVER_ERROR));
//
//
//    }
//
//
//    public static ResponseEntity error(Exception e, HttpServletRequest req) {
//
//        LOGGER.error("Failed generate error response", e.getMessage());
//
//        HashMap<String, Object> map = new HashMap<String, Object>(4);
//
//        List<ErrorResponse> errorResponses = new ArrayList<>();
//        ErrorResponse errorResponse = new ErrorResponse();
//        errorResponse.setId(0);
//        errorResponse.setTitle(e.getMessage());
//        errorResponse.setDetail(e.getMessage());
//        errorResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.name());
//        errorResponse.setCode(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()));
//
//        errorResponses.add(errorResponse);
//
//        map.put(ERRORS, errorResponses);
//        map.put(SUCCESS, false);
//
//        String data = writeValueToMapper(map);
//
//        return (new ResponseEntity<String>(data, HttpStatus.INTERNAL_SERVER_ERROR));
//
//
//    }
//
//
//    public static ResponseEntity error(String response) {
//
//        HashMap<String, Object> map = new HashMap<String, Object>(4);
//        HashMap<String, Object> DATA = new HashMap<String, Object>(1);
//        Map<Object, Object> hm = new HashMap<Object, Object>();
//        List<String> list = new ArrayList<>();
//        map.put(SUCCESS, false);
//
//
//        try {
//            DATA = new ObjectMapper().readValue(response, HashMap.class);
//            map.put(ERRORS, DATA);
//        } catch (IOException e1) {
//            LOGGER.error("Failed generate error response", e1.getMessage());
//            list.add(response);
//            hm.put("other", list);
//
//            JSONObject obj = new JSONObject(hm);
//            String responseOther = obj.toString();
//            try {
//                DATA = new ObjectMapper().readValue(responseOther, HashMap.class);
//            } catch (IOException e) {
//                LOGGER.error("Failed generate error response", e.getMessage());
//            }
//            map.put(ERRORS, DATA);
//        }
//
//
//        String data = writeValueToMapper(map);
//
//        return (new ResponseEntity<String>(data, HttpStatus.BAD_REQUEST));
//
//
//    }
//
////    public static ResponseEntity error(DataValidationEnum response, HttpServletRequest req) {
////
////        HashMap<String, Object> map = new HashMap<String, Object>(4);
////
////        List<ErrorResponse> errorResponses = new ArrayList<>();
////        ErrorResponse errorResponse = new ErrorResponse();
////        if (response != null) {
////            errorResponse.setId(response.getId());
////            errorResponse.setTitle(response.getName());
////            errorResponse.setDetail(response.getDescription());
////        }
////        errorResponse.setStatus(HttpStatus.BAD_REQUEST.name());
////        errorResponse.setCode(Integer.toString(HttpStatus.BAD_REQUEST.value()));
////
////
////        errorResponses.add(errorResponse);
////
////        map.put(ERRORS, errorResponses);
////        map.put(SUCCESS, false);
////
////        String data = writeValueToMapper(map);
////
////        return (new ResponseEntity<>(data, HttpStatus.BAD_REQUEST));
////
////
////    }
//
//    public static ResponseEntity success() {
//
//        HashMap<String, Object> map = new HashMap<String, Object>(1);
//        map.put(SUCCESS, true);
//
//        String data = writeValueToMapper(map);
//
//        return (new ResponseEntity<String>(data, HttpStatus.OK));
//
//    }
//
//    public static ResponseEntity success(int count) {
//
//        HashMap<String, Object> map = new HashMap<String, Object>(1);
//        map.put(SUCCESS, true);
//        map.put(TOTAL, count);
//
//        String data = writeValueToMapper(map);
//
//        return (new ResponseEntity<String>(data, HttpStatus.OK));
//
//    }
//
//
//    private static String writeValueToMapper(HashMap<String, Object> map) {
//
//        String data = "";
//        try {
//            ObjectMapper mapper = new ObjectMapper();
//            data = mapper.writeValueAsString(map);
//        } catch (Exception e) {
//            LOGGER.error("Failed generate error response:{}", e.getMessage());
//        }
//
//        return data;
//    }
//
//
//}
