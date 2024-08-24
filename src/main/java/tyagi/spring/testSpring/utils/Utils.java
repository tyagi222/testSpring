package tyagi.spring.testSpring.utils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import tyagi.spring.testSpring.helper.CustomPage;

@Component
@RequiredArgsConstructor
public class Utils {

    private final ModelMapper modelMapper;

    public <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
        return source.stream().map(element -> modelMapper.map(element, targetClass)).collect(Collectors.toList());
    }

    public <S, T> List<T> mapSet(Set<S> source, Class<T> targetClass) {
        return source.stream().map(element -> modelMapper.map(element, targetClass)).collect(Collectors.toList());
    }

    public <D, T> CustomPage<D> mapEntityPageIntoDtoPage(Page<T> entities, Class<D> targetClass) {
        Page<D> customPage = entities.map(objectEntity -> modelMapper.map(objectEntity, targetClass));
        return new CustomPage<D>(customPage);
    }
}
