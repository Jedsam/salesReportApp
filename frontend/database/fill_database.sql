INSERT INTO USERS (user_id, email, password_hash)
VALUES (X'11111111111111111111111111111111', 'email@example.com', 'hashedpassword123');

INSERT INTO MERCHANTS (merchant_id, user_id, name, business_name, phone, address, created_at, status)
VALUES (X'22222222222222222222222222222222', X'11111111111111111111111111111111', 'Name', 'Business Name', '+900000000000', 'Address', datetime('now'), 'active');

INSERT INTO SHOPS (shop_id, merchant_id, name, address, phone, email, created_at, status)
VALUES (X'33333333333333333333333333333333', X'22222222222222222222222222222222', 'Shop Name', 'Address', '+900000000001', 'shopemail@example.com', datetime('now'), 'active');

INSERT INTO MODEL (model_id, model_name)
VALUES (X'44444444444444444444444444444444', 'ModelName1000');

INSERT INTO FIRMWARE (firmware_id, firmware_version)
VALUES (X'55555555555555555555555555555555', 'v1.0.0-temp');

INSERT INTO DEVICES (device_id, shop_id, firmware_id, model_id, last_seen, created_at, status)
VALUES (X'66666666666666666666666666666666', X'33333333333333333333333333333333', X'55555555555555555555555555555555', X'44444444444444444444444444444444', datetime('now'), datetime('now'), 'active');

