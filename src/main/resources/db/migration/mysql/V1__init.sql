CREATE TABLE `customer` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `creation_date` datetime NOT NULL,
  `last_update_date` datetime NOT NULL,
  `profile` varchar(50) NOT NULL,
  `main_payment_method` varchar(50)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `orders` (
  `id` bigint(20) NOT NULL,
  `quantity` bigint(20) NOT NULL,
  `customer_id` bigint(20)  NOT NULL,
  `shipping_method` varchar(50) NOT NULL,
  `payment_method` varchar(50)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `product` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `main_image` varchar(255) NOT NULL,
  `price` float(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `order_item` (
  `id` bigint(20) NOT NULL,
  `order_id` bigint(20)  NOT NULL,
  `product_id` bigint(20) NOT NULL,
  `quantity` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
ADD PRIMARY KEY (`id`);

--
-- Indexes for table `product`
--
ALTER TABLE `product`
ADD PRIMARY KEY (`id`);

--
-- Indexes for table `order_item`
--
ALTER TABLE `order_item`
ADD PRIMARY KEY (`id`);

-- AUTO_INCREMENT for table `customer`
--
ALTER TABLE `customer`
MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

-- AUTO_INCREMENT for table `orders`
--
ALTER TABLE `orders`
MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

-- AUTO_INCREMENT for table `product`
--
ALTER TABLE `product`
MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

-- AUTO_INCREMENT for table `order_item`
--
ALTER TABLE `order_item`
MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;