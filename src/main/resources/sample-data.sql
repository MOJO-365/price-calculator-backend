INSERT INTO ${company}.panel (model, manufacturer, warranty_in_months, watt, price)
VALUES
('P-1000', 'SolarTech', 120, 350, 199.99),
('P-2000', 'GreenEnergy', 144, 400, 249.99),
('P-3000', 'EcoSolar', 180, 450, 299.99),
('P-4000', 'SunPower', 200, 500, 349.99),
('P-5000', 'BrightFuture', 220, 550, 399.99);

INSERT INTO ${company}.battery (model, manufacturer, warranty_in_months, price, capacity)
VALUES
('B-100', 'PowerCell', 60, 499.99, '5 kWh'),
('B-200', 'EcoBattery', 72, 599.99, '7 kWh'),
('B-300', 'GreenVolt', 84, 699.99, '10 kWh'),
('B-400', 'SolarReserve', 96, 799.99, '12 kWh'),
('B-500', 'EnergySafe', 108, 899.99, '15 kWh');

INSERT INTO ${company}.inverter (model, manufacturer, price, warranty_in_months, updated_at, created_at)
VALUES
('I-100', 'VoltMaster', 999, 60, 1710000000, 1700000000),
('I-200', 'SolarConvert', 1199, 72, 1710100000, 1700100000),
('I-300', 'GreenSwitch', 1399, 84, 1710200000, 1700200000),
('I-400', 'EcoFlow', 1599, 96, 1710300000, 1700300000),
('I-500', 'PowerBridge', 1799, 108, 1710400000, 1700400000);

INSERT INTO ${company}.Price (name, unit, price)
VALUES
('Solar Panel', 'per unit', 199.99),
('Battery Pack', 'per unit', 599.99),
('Inverter', 'per unit', 1199.99),
('Installation', 'per system', 499.99),
('Maintenance', 'per year', 299.99);
