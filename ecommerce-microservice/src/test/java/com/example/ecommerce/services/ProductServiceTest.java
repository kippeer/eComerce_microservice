package com.example.ecommerce.services;

import com.example.ecommerce.dtos.ProductDTO;
import com.example.ecommerce.exceptions.ResourceNotFoundException;
import com.example.ecommerce.mappers.ProductMapper;
import com.example.ecommerce.models.Product;
import com.example.ecommerce.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductMapper productMapper;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllProducts_Success() {
        // Arrange
        Product product1 = new Product();
        Product product2 = new Product();
        List<Product> products = Arrays.asList(product1, product2);

        ProductDTO productDTO1 = new ProductDTO();
        ProductDTO productDTO2 = new ProductDTO();
        List<ProductDTO> expectedDTOs = Arrays.asList(productDTO1, productDTO2);

        when(productRepository.findAll()).thenReturn(products);
        when(productMapper.toDTO(product1)).thenReturn(productDTO1);
        when(productMapper.toDTO(product2)).thenReturn(productDTO2);

        // Act
        List<ProductDTO> result = productService.getAllProducts();

        // Assert
        assertEquals(expectedDTOs.size(), result.size());
        verify(productRepository).findAll();
    }

    @Test
    void getProductById_Success() {
        // Arrange
        Long productId = 1L;
        Product product = new Product();
        product.setId(productId);
        product.setName("Test Product");
        product.setPrice(BigDecimal.TEN);

        ProductDTO expectedDTO = new ProductDTO();
        expectedDTO.setId(productId);
        expectedDTO.setName("Test Product");
        expectedDTO.setPrice(BigDecimal.TEN);

        when(productRepository.findById(productId)).thenReturn(Optional.of(product));
        when(productMapper.toDTO(product)).thenReturn(expectedDTO);

        // Act
        ProductDTO result = productService.getProductById(productId);

        // Assert
        assertNotNull(result);
        assertEquals(productId, result.getId());
        assertEquals("Test Product", result.getName());
    }

    @Test
    void getProductById_NotFound() {
        // Arrange
        Long productId = 999L;
        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> productService.getProductById(productId));
    }
}