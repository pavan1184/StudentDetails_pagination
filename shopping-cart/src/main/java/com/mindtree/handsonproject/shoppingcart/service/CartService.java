package com.mindtree.handsonproject.shoppingcart.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mindtree.handsonproject.shoppingcart.dto.AddCartDto;
import com.mindtree.handsonproject.shoppingcart.dto.CartProductDto;
import com.mindtree.handsonproject.shoppingcart.dto.GetCartProduct;
import com.mindtree.handsonproject.shoppingcart.entity.Cart;
import com.mindtree.handsonproject.shoppingcart.entity.Product;
import com.mindtree.handsonproject.shoppingcart.entity.Quantity;
import com.mindtree.handsonproject.shoppingcart.entity.User;
import com.mindtree.handsonproject.shoppingcart.exception.CartNotFound;
import com.mindtree.handsonproject.shoppingcart.exception.ProductNotFound;
import com.mindtree.handsonproject.shoppingcart.exception.QuantityNotFound;
import com.mindtree.handsonproject.shoppingcart.exception.UserNotFound;
import com.mindtree.handsonproject.shoppingcart.repository.CartRepository;
import com.mindtree.handsonproject.shoppingcart.repository.ProductRepository;
import com.mindtree.handsonproject.shoppingcart.repository.QuantityRepository;
import com.mindtree.handsonproject.shoppingcart.repository.UserRepository;

@Service
@Transactional
public class CartService {

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private QuantityRepository quantityRepository;

	@Autowired
	private ProductRepository productRepository;

	private Logger logger = LoggerFactory.getLogger(CartService.class);

	/**
	 * This function Find All the products which was added by user into there cart
	 * 
	 * @param id
	 * @return products added into there cart with the specified quantity
	 */
	@Transactional
	public GetCartProduct getAll(int id) {
		GetCartProduct getCart = new GetCartProduct();
		try {
			Optional<User> findById = userRepository.findById(id);
			if (!findById.isPresent()) {
				throw new UserNotFound("No, User exsits  with this id " + id);
			}
			Optional<Cart> findByUser = cartRepository.findByUser(findById.get());

			findByUser.orElseThrow(() -> new CartNotFound("No cart Available with this id" + id));

			Optional<List<Quantity>> findByCart = quantityRepository.findByCart(findByUser.get());
			List<Quantity> list = findByCart.get();

			List<CartProductDto> map = list.stream()
					.map(quantity -> new CartProductDto(quantity.getProduct(), quantity.getQuantity()))
					.collect(Collectors.toList()); // mapping products with user required quantity

			getCart.setProducts(map);
			getCart.setTotalAmount(findByUser.get().getPrice());
		} catch (UserNotFound | CartNotFound e) {
			logger.error(e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return getCart;
	}

	/**
	 * It can perform Add to cart functionality.
	 * 
	 * @param cartdto Take product and user object as attribute and add item into
	 *                cart
	 * @return String status either product added into cart or something went wrong
	 *         while adding item.
	 */

	@Transactional
	public String saveToCart(AddCartDto cartdto) {

		String status = "Somthong went Wrong! product not Added into cart.";

		try {
			Optional<Cart> findByUser = cartRepository.findByUser(cartdto.getUser());
			findByUser.orElseThrow(
					() -> new CartNotFound("No such cart Associated with this id" + cartdto.getUser().getFirstName()));
			Cart cart = findByUser.get();
			List<Product> products = cart.getProducts();
			boolean anyMatch = products.stream()
					.anyMatch(name -> name.getProdName().equals(cartdto.getProduct().getProdName()));
			if (!anyMatch) {
				cart.getProducts().add(cartdto.getProduct()); // adding product into cart if it's already not added
			}

			cart.setPrice(cart.getPrice() + cartdto.getProduct().getPrice()); // updating cart grand price total
			cart.setQty(cart.getQty() + 1); // updating cart grand quantity total

			Optional<Quantity> findByProduct = quantityRepository.findByProduct(cartdto.getProduct());

			Quantity quantity = new Quantity(); // adding product quantity
			if (findByProduct.isPresent()) { // checking if product already present then just update quantity by one
				quantity = findByProduct.get();
				quantity.setQuantity(quantity.getQuantity() + 1);
			} else { // or if not, then storing product and quantity
				quantity.setCart(cart);
				quantity.setProduct(cartdto.getProduct());
				quantity.setQuantity(1);
			}
			quantity = quantityRepository.save(quantity);
			if (findByProduct.isPresent()) {
			} else {
				cart.getQuantity().add(quantity);
				cartRepository.save(cart);
			}
			status = cartdto.getProduct().getProdName() + " Added successfully into cart.";

		} catch (CartNotFound e) {
			logger.error(e.getMessage());
		}

		return status;

	}

	/**
	 * This function can remove the product from user cart.
	 * 
	 * @param cartdto Take product and user object as attribute and add item into
	 *                cart
	 * @return String status either product remove or something went wrong.
	 */

	@Transactional
	public String deletefromCart(AddCartDto cartdto) {

		String status = "Something went Wrong! Product Could not deleted from cart.";

		try {

			Optional<Cart> findByUser = cartRepository.findByUser(cartdto.getUser());
			findByUser.orElseThrow(
					() -> new CartNotFound("No such cart Associated with this id " + cartdto.getUser().getFirstName()));
			Cart cart = findByUser.get();
			Optional<Product> product = productRepository.findById(cartdto.getProduct().getProductId());

			product.orElseThrow(
					() -> new ProductNotFound("No Such Product Found with id " + cartdto.getProduct().getProductId()));

			Optional<Quantity> findByProduct = quantityRepository.findByProduct(product.get());
			findByProduct.orElseThrow(
					() -> new QuantityNotFound("No Quantity Available with respect of " + product.get().getProdName()));
			Quantity quantity = findByProduct.get();
			cart.setPrice(cart.getPrice() - (quantity.getProduct().getPrice() * quantity.getQuantity())); // updating user cart grand price total

			cart.getQuantity().remove(quantity); // removing product from cart

			quantityRepository.delete(findByProduct.get());

			cartRepository.save(cart);

			status = cartdto.getProduct().getProdName() + " Successfully removed from your cart";

		} catch (CartNotFound e) {
			logger.error(e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return status;
	}

	
	/**
	 * This function can handle updating cart quantity
	 * if cart quantity below 0  or equal to 0 then it can remove product from user cart
	 * by Calling deleteCart functionality
	 * 
	 * @param cartdto
	 * @return String status either cart updated successfully or something went wrong.
	 */
	
	@Transactional
	public String updatingCartProductQuantity(AddCartDto cartdto) {

		String status = "Something went wrong! Your Cart item not updated";
		try {

			if (cartdto.getQuantity() <= 0) {
				status = this.deletefromCart(cartdto);
			} else {
				Optional<Cart> findByUser = cartRepository.findByUser(cartdto.getUser()); // finding user cart
				findByUser.orElseThrow(() -> new CartNotFound(
						"No such cart Associated with this id " + cartdto.getUser().getFirstName()));
				Cart cart = findByUser.get(); 

				Optional<Quantity> findByProduct = quantityRepository.findByProduct(cartdto.getProduct()); // finding product already exist into cart

				Quantity quantity = new Quantity();
				findByProduct.orElseThrow(() -> new QuantityNotFound(
						"No Quantity Available with respect of " + cartdto.getProduct().getProdName()));
				quantity = findByProduct.get();
				quantity.setQuantity(cartdto.getQuantity()); // updating product quantity
				quantityRepository.save(quantity);
				Optional<List<Quantity>> findByCart = quantityRepository.findByCart(findByUser.get()); // fetching list of all product already exist into cart
				List<Quantity> list = findByCart.get();

				cart.setPrice(0); // initialize cart grand price total 0
				cart.setQty(0);  // initialize cart grand quantity total 0

				list.stream().forEach(quant -> cart
						.setPrice(cart.getPrice() + (quant.getProduct().getPrice() * quant.getQuantity()))); // updating cart grand price total with an updated product quantity into cart
				list.stream().forEach(quant -> cart.setQty(cart.getQty() + quant.getQuantity())); // updating cart grand quantity total with an updated product quantity into cart
				cartRepository.save(cart);

				status = cartdto.getProduct().getProdName() + " successfully updated with the quantity of "
						+ cartdto.getQuantity();
			}
		} catch (CartNotFound | QuantityNotFound e) {
			logger.error(e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return status;
	}

}
