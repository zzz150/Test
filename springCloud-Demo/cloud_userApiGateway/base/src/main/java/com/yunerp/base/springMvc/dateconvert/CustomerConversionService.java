package com.yunerp.base.springMvc.dateconvert;

import java.util.Set; 

import javax.annotation.PostConstruct;

import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.core.convert.converter.GenericConverter;
import org.springframework.core.convert.support.GenericConversionService;
/**
 * <p>功能描述：为自定义的转换器注册到
 * DefaultAnnotationHandlerMapping与
 * AnnotationMethodHandlerAdapter 中去<p>
 * @author jianghang
 *
 */
public class CustomerConversionService implements ConversionService{

	 	private GenericConversionService conversionService;
	    
	    private Set<?> converters;
	    
		public boolean canConvert(Class<?> sourceType, Class<?> targetType) {
			return conversionService.canConvert(sourceType,targetType);
		}
		
		@PostConstruct
	    public void afterPropertiesSet() {
	       if (converters != null) {
	           for (Object converter : converters) {
	              if (converter instanceof Converter<?, ?>) {
	                  conversionService.addConverter((Converter<?, ?>)converter);
	              } else if (converter instanceof ConverterFactory<?, ?>) {
	                  conversionService.addConverterFactory((ConverterFactory<?, ?>)converter);
	              } else if (converter instanceof GenericConverter) {
	                  conversionService.addConverter((GenericConverter)converter);
	              }
	           }
	       }
	    };	
		public boolean canConvert(TypeDescriptor sourceType, TypeDescriptor targetType) {
			return conversionService.canConvert(sourceType, targetType);
		}

		public <T> T convert(Object source, Class<T> targetType) {
			return conversionService.convert(source, targetType);
		}

		public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
			return conversionService.convert(source, sourceType, targetType);
		}

		public GenericConversionService getConversionService() {
			return conversionService;
		}

		public void setConversionService(GenericConversionService conversionService) {
			this.conversionService = conversionService;
		}

		public Set<?> getConverters() {
			return converters;
		}

		public void setConverters(Set<?> converters) {
			this.converters = converters;
		}

}
