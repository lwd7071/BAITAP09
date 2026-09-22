package vn.iotstar.mapper;
import org.mapstruct.*;
import vn.iotstar.dto.ProductDTO;
import vn.iotstar.entity.Product;
@Mapper(componentModel="spring", unmappedTargetPolicy=ReportingPolicy.IGNORE)
public interface ProductMapper { @Mapping(target="categoryId",source="category.id") @Mapping(target="categoryName",source="category.name") ProductDTO toDto(Product product); }
