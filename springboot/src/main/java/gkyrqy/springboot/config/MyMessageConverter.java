package gkyrqy.springboot.config;

import gkyrqy.springboot.service.Person;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;

public class MyMessageConverter extends AbstractHttpMessageConverter<Person> {


    public MyMessageConverter() {
        //新建一个我们自定义的媒体类型application/x-wisely
        // super(new MediaType("application", "x-wisely", Charset.forName("UTF-8")));
    }

    @Override
    protected boolean supports(Class<?> aClass) {
        return true;
    }

    @Override
    protected Person readInternal(Class<? extends Person> aClass, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {
        Person person = new Person();
        person.setName("flsdkfj;asldkjf");
        return person;
    }

    @Override
    protected void writeInternal(Person person, HttpOutputMessage httpOutputMessage) throws IOException, HttpMessageNotWritableException {
        String out = person.toString() + "ffffff";
        httpOutputMessage.getBody().write(out.getBytes());
    }
}
