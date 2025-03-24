CREATE SCHEMA IF NOT EXISTS ${company};

CREATE TABLE IF NOT EXISTS ${company}.panel (
    id SERIAL PRIMARY KEY,
    model VARCHAR(255) NOT NULL,
    manufacturer VARCHAR(255) NOT NULL,
    warranty_in_months INT NOT NULL,
    watt INT NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS ${company}.battery (
    id SERIAL PRIMARY KEY ,
    model VARCHAR(100) NOT NULL,
    manufacturer VARCHAR(100) NOT NULL,
    warranty_in_months INT NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    capacity_in_watt INT(50) NOT NULL,
    phase VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS ${company}.inverter (
    id SERIAL PRIMARY KEY,
    model VARCHAR(255) NOT NULL,
    manufacturer VARCHAR(255) NOT NULL,
    price INT NOT NULL,
    warranty_in_months INT NOT NULL,
    updated_at BIGINT NOT NULL,
    created_at BIGINT NOT NULL,
    phase VARCHAR(50) NOT NULL,
    mppt INT NOT NULL,
    is_hybrid VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS ${company}.price (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    sub_id VARCHAR(255),
    updated_at BIGINT,
    msg VARCHAR(255),
    nsw JSONB,
    qld JSONB,
    act JSONB,
    vic JSONB,
    wa JSONB,
    sa JSONB,
    tas JSONB,
    nt JSONB,
    jbt JSONB
);

CREATE TABLE IF NOT EXISTS ${company}.warehouse (
    id SERIAL PRIMARY KEY,
    warehouse_name VARCHAR(255),
    address VARCHAR(255),
    postcode INTEGER,
    state VARCHAR(3),
    latitude VARCHAR(255)
    longitude VARCHAR(255)
    is_active BOOLEAN,
    updated_at BIGINT,
    created_at BIGINT
);

CREATE TABLE ${company}.questions (
    id SERIAL PRIMARY KEY,
    company VARCHAR(255) NOT NULL,
    customer_name VARCHAR(255) NOT NULL,
    phone_number VARCHAR(20) NOT NULL,
    question JSON NOT NULL
);

--CREATE TABLE ${company}.questions (
--    id INT PRIMARY KEY,
--    title TEXT NOT NULL,
--    diffWaysToSay JSON NOT NULL,
--    answerType VARCHAR(20) NOT NULL,
--    doesTriggerPrice BOOLEAN NOT NULL,
--    priceChange INT CHECK (priceChange >= 0),
--    unit VARCHAR(50),
--    placeholder VARCHAR(100) NOT NULL,
--    image_link TEXT
--);


--CREATE TABLE ${company}.distributor (
--   id bigint NOT NULL AUTO_INCREMENT,
--   organization_id bigint DEFAULT NULL,
--   name varchar(250) NOT NULL,
--   location_id bigint NOT NULL,
--   email varchar(250) DEFAULT NULL,
--   distributor_abb varchar(250) DEFAULT NULL,
--   distributor_app_req tinyint DEFAULT '0',
--   meter_app_exp_days varchar(20) DEFAULT NULL,
--   created_at timestamp NULL DEFAULT CURRENT_TIMESTAMP,
--   updated_at timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
--   active_status tinyint DEFAULT NULL,
--   gb_id smallint DEFAULT NULL,
--   network_area_loss double DEFAULT NULL,
--   ut_code varchar(10) DEFAULT NULL,
--  PRIMARY KEY (`id`),
--  UNIQUE KEY UNIQUE (`organization_id`,`name`,`location_id`,`email`)
--)
--
--CREATE TABLE ${company}.distributor_post_codes (
--   id bigint NOT NULL AUTO_INCREMENT,
--   distributor_id bigint DEFAULT NULL,
--   post_code bigint DEFAULT NULL,
--   PRIMARY KEY (`id`)
--)
--
--CREATE TABLE ${company}.exist_system_inverter_details (
--  `id` bigint NOT NULL AUTO_INCREMENT,
--  `project_id` bigint DEFAULT NULL,
--  `no_of_inverters` double DEFAULT NULL,
--  `inverter_models` varchar(100) DEFAULT NULL,
--  `inverter_size` double DEFAULT NULL,
--  `inverter_serial_no` double DEFAULT NULL,
--  `inverter_reading_day` double DEFAULT NULL,
--  `inverter_reading_far` double DEFAULT NULL,
--  `created_by` bigint DEFAULT NULL,
--  `created_on` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
--  `updated_by` bigint DEFAULT NULL,
--  `updated_on` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
--  `active_status` tinyint DEFAULT NULL,
--  PRIMARY KEY (`id`)
--)
--
--CREATE TABLE ${company}.exist_system_panel_details (
--  `id` bigint NOT NULL AUTO_INCREMENT,
--  `project_id` bigint DEFAULT NULL,
--  `no_of_panels` double DEFAULT NULL,
--  `working_panels` double DEFAULT NULL,
--  `panel_models` varchar(100) DEFAULT NULL,
--  `panel_manufacturer` varchar(100) DEFAULT NULL,
--  `panel_size` double DEFAULT NULL,
--  `panel_orientation` double DEFAULT NULL,
--  `output_string` varchar(100) DEFAULT NULL,
--  `created_by` bigint DEFAULT NULL,
--  `created_on` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
--  `updated_by` bigint DEFAULT NULL,
--  `updated_on` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
--  `active_status` tinyint DEFAULT NULL,
--  PRIMARY KEY (`id`)
--)
--
--CREATE TABLE ${company}.`house_type` (
--  `id` bigint NOT NULL AUTO_INCREMENT,
--  `name` varchar(200) DEFAULT NULL,
--  `house_type_abb` varchar(10) DEFAULT NULL,
--  `organization_id` bigint DEFAULT NULL,
--  `created_on` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
--  `updated_on` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
--  `created_by` bigint DEFAULT NULL,
--  `updated_by` bigint DEFAULT NULL,
--  `active_status` tinyint DEFAULT NULL,
--  `migration_id` bigint DEFAULT NULL,
--  PRIMARY KEY (`id`)
--)
--
--CREATE TABLE ${company}.`manufacturer` (
--  `id` bigint NOT NULL AUTO_INCREMENT,
--  `supplier_name` varchar(150) NOT NULL,
--  `abn` bigint DEFAULT NULL,
--  `prefix` varchar(60) DEFAULT NULL,
--  `email` varchar(45) DEFAULT NULL,
--  `first_name` varchar(45) DEFAULT NULL,
--  `last_name` varchar(45) DEFAULT NULL,
--  `supplier_contact` bigint DEFAULT NULL,
--  `mobile_number` bigint DEFAULT NULL,
--  `product_category_id` bigint DEFAULT NULL,
--  `account_number` bigint DEFAULT NULL,
--  `address_id` bigint DEFAULT NULL,
--  `created_on` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
--  `updated_on` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
--  `created_by` bigint DEFAULT NULL,
--  `updated_by` bigint DEFAULT NULL,
--  `active_status` tinyint DEFAULT NULL,
--  PRIMARY KEY (`id`)
--)
--
--CREATE TABLE ${company}.`office_location` (
--  `id` bigint NOT NULL AUTO_INCREMENT,
--  `name` varchar(45) DEFAULT NULL,
--  `address_id` bigint DEFAULT NULL,
--  `is_office_location` tinyint DEFAULT NULL,
--  `is_warehouse_location` tinyint DEFAULT NULL,
--  `state` varchar(45) DEFAULT NULL,
--  `created_on` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
--  `updated_on` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
--  `created_by` bigint DEFAULT NULL,
--  `updated_by` bigint DEFAULT NULL,
--  `active_status` tinyint DEFAULT NULL,
--  PRIMARY KEY (`id`)
--)
--
--CREATE TABLE ${company}.`organizations` (
--  `id` bigint NOT NULL AUTO_INCREMENT,
--  `name` varchar(80) NOT NULL,
--  `abn` varchar(15) DEFAULT NULL,
--  `prefix` varchar(5) DEFAULT '',
--  `name_as_per_abn` varchar(50) DEFAULT NULL,
--  `contact_person` varchar(45) DEFAULT NULL,
--  `contact_email` varchar(45) DEFAULT NULL,
--  `mobile` varchar(20) DEFAULT NULL,
--  `phone_number` varchar(20) DEFAULT NULL,
--  `address_id` bigint DEFAULT NULL,
--  `logo_filename` varchar(45) DEFAULT NULL,
--  `active_status` tinyint DEFAULT NULL,
--  `created_on` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
--  `updated_on` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
--  `created_by` bigint DEFAULT NULL,
--  `updated_by` bigint DEFAULT NULL,
--  `website` varchar(45) DEFAULT NULL,
--  `acp_id` bigint DEFAULT NULL,
--  PRIMARY KEY (`id`)
--)
--
--
--CREATE TABLE ${company}.retailer (
--  id bigint NOT NULL AUTO_INCREMENT,
--  organization_id bigint DEFAULT NULL,
--  name varchar(250) NOT NULL,
--  location_id bigint NOT NULL,
--  email varchar(250) NOT NULL,
--  contact_no varchar(10) DEFAULT NULL,
--  address_id bigint DEFAULT NULL,
--  created_on timestamp NULL DEFAULT CURRENT_TIMESTAMP,
--  updated_on timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
--  created_by bigint DEFAULT NULL,
--  updated_by bigint DEFAULT NULL,
--  active_status tinyint DEFAULT NULL,
--  migration_id tinyint DEFAULT NULL,
--  gb_id smallint DEFAULT NULL,
--  PRIMARY KEY (id),
--  UNIQUE KEY `UNIQUE` (organization_id,name,location_id,email),
--  KEY retailer_org_idx (organization_id)
--)