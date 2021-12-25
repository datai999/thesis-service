package com.thesis.service.dto;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Component;

@Component
public class ModelConverter {

  private ModelMapper modelMapper;

  public ModelConverter() {
    this.modelMapper = new ModelMapper();
    this.modelMapper.getConfiguration().setSkipNullEnabled(false);
  }

  /**
   * Maps {@code source} to {@code destination}.
   * <p>
   * Note: {@code destination} object must have default constructor with no
   * arguments
   * </p>
   *
   * @param <S> {@code source} type
   * @param <D> {@code destination} type
   * @param source object to map from
   * @param destinationType type to map to
   * @return new object of {@code destination} type.
   */
  public <D, S> D map(final S source, Class<D> destinationType) {
    return Objects.isNull(source)
        ? null
        : modelMapper.map(source, destinationType);
  }

  /**
   * Maps {@code source} to {@code destination}.
   *
   * @param <S> {@code source} type
   * @param <D> {@code destination} type
   * @param source object to map from
   * @param destination object to map to
   */
  public <D, S> D map(final S source, D destination) {
    modelMapper.map(source, destination);
    return destination;
  }

  /**
   * <p>
   * map {@code list of element} from {@code Collection<S> sourceList} into list
   * of mapped object with {@code D} type by iterating the {@code sourceList}
   * </p>
   *
   * @param sourceList list of object to map from
   * @param destinationType type to map to
   * @param <D> destination type
   * @param <S> {@code sourceList} type
   * @return list of mapped object with {@code D} type.
   */
  public <D, S> List<D> map(final Collection<S> sourceList, Class<D> destinationType) {
    return Objects.nonNull(sourceList)
        ? sourceList.parallelStream().map(source -> this.map(source, destinationType))
            .collect(Collectors.toList())
        : null;
  }

  /**
   * <p>
   * map {@code list of element} from {@code Collection<S> sourceList} into list
   * of mapped object with {@code D} type by {@link TypeToken}
   * </p>
   *
   * @param sourceList list of object to map from
   * @param destinationType type to map to
   * @param <D> destination type
   * @param <S> {@code sourceList} type
   * @return list of mapped object with {@code D} type.
   */
  public <D, S> List<D> map(final Collection<S> sourceList) {
    return modelMapper.map(sourceList, new TypeToken<List<D>>() {}.getType());
  }

  public <D, E> List<D> map(final Collection<E> sourceList, Function<E, D> mapping) {
    return Objects.nonNull(sourceList)
        ? this.map(sourceList.parallelStream().map(mapping).collect(Collectors.toList()))
        : List.of();
  }

  public List<String> map(final MultiLangDto multiLang) {
    if (Objects.isNull(multiLang)
        || (Objects.isNull(multiLang.getVi()) && Objects.isNull(multiLang.getEn())))
      return List.of();
    if (Objects.isNull(multiLang.getEn()))
      return List.of(multiLang.getVi());
    if (Objects.isNull(multiLang.getVi()))
      return List.of(multiLang.getEn());
    return List.of(multiLang.getVi(), multiLang.getEn());
  }

}
