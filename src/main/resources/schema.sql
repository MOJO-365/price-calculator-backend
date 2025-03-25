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
    capacity_in_watt INT NOT NULL,
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