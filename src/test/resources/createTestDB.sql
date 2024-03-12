

-- ChangeSet: create app_role table
CREATE TABLE app_role (
                                        app_role_id uuid PRIMARY KEY NOT NULL,
                                        name VARCHAR(45) UNIQUE NOT NULL,
                                        possibilities TEXT NOT NULL
    );

-- ChangeSet: create courier table
CREATE TABLE courier (
                                        courier_id uuid PRIMARY KEY NOT NULL UNIQUE,
                                        courier_name VARCHAR(45) NOT NULL UNIQUE,
                                        password VARCHAR(255),
                                        registration_date DATE NOT NULL,
                                        delivery_zone_id uuid,
                                        courier_status VARCHAR(36) NOT NULL,
                                        phone_number VARCHAR(45) UNIQUE NOT NULL,
                                        balance DECIMAL(6,2) NOT NULL,
                                        statistics_id uuid UNIQUE,
                                        support_manager_id uuid,
                                        app_role_id uuid
    );

-- ChangeSet: create delivery_zone table
CREATE TABLE delivery_zone (
                                        delivery_zone_id uuid PRIMARY KEY NOT NULL UNIQUE,
                                        delivery_zone_name VARCHAR(45) NOT NULL,
                                        restaurants TEXT NOT NULL
    );

-- ChangeSet: create manager table
CREATE TABLE manager (
                                        manager_id uuid PRIMARY KEY NOT NULL UNIQUE,
                                        manager_name VARCHAR(45) NOT NULL,
                                        password VARCHAR(255),
                                        app_role_id uuid NOT NULL
    );

-- ChangeSet: create order table
CREATE TABLE "order" (
                                        order_id uuid PRIMARY KEY NOT NULL UNIQUE,
                                        courier_id uuid,
                                        restaurant_id uuid NOT NULL,
                                        order_status VARCHAR(36) NOT NULL,
                                        placed_at TIMESTAMP NOT NULL,
                                        delivered_at TIMESTAMP
    );

-- ChangeSet: create product table
CREATE TABLE product (
                                        product_id uuid PRIMARY KEY NOT NULL UNIQUE,
                                        product_name VARCHAR(45) NOT NULL,
                                        product_price VARCHAR(36) NOT NULL,
                                        restaurant_id uuid NOT NULL,
                                        description TEXT NOT NULL
    );

ALTER TABLE product ALTER COLUMN product_price TYPE float USING product_price::float;

-- ChangeSet: create restaurant table
CREATE TABLE restaurant (
                                        restaurant_id uuid PRIMARY KEY NOT NULL UNIQUE,
                                        restaurant_name VARCHAR(45) NOT NULL,
                                        owner VARCHAR(45) NOT NULL,
                                        app_role_id uuid NOT NULL,
                                        restaurant_status VARCHAR(36) NOT NULL,
                                        creation_date DATE NOT NULL,
                                        delivery_zone_id uuid NOT NULL
    );

-- ChangeSet: create statistics table
CREATE TABLE statistics (
                                        statistics_id uuid PRIMARY KEY NOT NULL UNIQUE,
                                        courier_id uuid NOT NULL,
                                        start_date DATE NOT NULL,
                                        till DATE NOT NULL,
                                        completed_deliveries INT,
                                        earned_money DECIMAL(6,2) NOT NULL
    );

-- Insert data to test db app_role

-- ChangeSet: insert test data into app_role table 1
INSERT INTO app_role (app_role_id, name, possibilities)
VALUES ('53da7e2b-7e7f-421e-8b5e-371dd13c2b64', 'courier', 'can go online/offline, accept or reject incoming orders, manage app
         settings, use functions such as picking up the orders, using call button and delivering the orders,
         he can also contact the support team and watch statistics');

-- ChangeSet: insert test data into app_role table 2
INSERT INTO app_role (app_role_id, name, possibilities)
VALUES ('f6604fdd-71db-4e8c-a884-61d7de2b40cc', 'support_manager', 'support manager has all the functions needed to control the orders:
         he has ability to decline or give orders, has access to all the information about current orders in any
         locations and currently working couriers and restaurants, he can also contact all couriers, restaurants
         and customers if needed, as well as providing recompenses for them');

-- ChangeSet: insert test data into app_role table 3
INSERT INTO app_role (app_role_id, name, possibilities)
VALUES ('60c9bbdd-f631-414f-a12e-63ed1119b264', 'restaurant', 'restaurant can receive and accept orders as well as courier,
         it can also contact support team, use call button for couriers and customers and watch own
         statistics. Restaurant can also set time, which is supposed to represent the moment in which an
         order is going to be ready for pick up.');

-- Insert data to test db courier

-- ChangeSet: insert test data into courier table 1
INSERT INTO courier (courier_id, courier_name, password, registration_date, delivery_zone_id, courier_status, phone_number, balance, statistics_id, support_manager_id, app_role_id)
VALUES ('6453d453-0e33-41ec-aebd-637c5e6bb786',
        'Anatoli Grenz',
        '$2a$12$ghMXeCpYNajCx8DCOqr1su8tmHBgU24FQ2k0Q.Z0TwS11XPqako9i',
        '2023-10-12',
        '7bdf2f58-17cd-4243-957e-1a3119ff53ad',
        'ONLINE',
        '+37123977865',
        0.0,
        '25e65c2d-0d88-41fb-a9b1-085d3a126318',
        '91069e9d-683b-4fd1-93bc-f135207b7e73',
        '53da7e2b-7e7f-421e-8b5e-371dd13c2b64');

-- ChangeSet: insert test data into courier table 2
INSERT INTO courier (courier_id, courier_name, password, registration_date, delivery_zone_id, courier_status, phone_number, balance, statistics_id, support_manager_id, app_role_id)
VALUES ('dca55a7d-a8db-4777-9daa-97c823277dbf',
        'Alex Karadin',
        '$2a$12$ghMXeCpYNajCx8DCOqr1su8tmHBgU24FQ2k0Q.Z0TwS11XPqako9i',
        '2023-10-24',
        '1ced69a9-4918-4ece-a06a-7c7996f4475a',
        'OFFLINE',
        '+37123309344',
        29.7,
        '89e23605-46a6-4e40-b48d-bb677fde1d2c',
        '4829faa8-4946-410b-b859-f595d537e949',
        '53da7e2b-7e7f-421e-8b5e-371dd13c2b64');

-- ChangeSet: insert test data into courier table 3
INSERT INTO courier (courier_id, courier_name, password, registration_date, delivery_zone_id, courier_status, phone_number, balance, statistics_id, support_manager_id, app_role_id)
VALUES ('8035c414-89a8-40e1-a914-83b65388a1f5',
        'Reno Logan',
        '$2a$12$ghMXeCpYNajCx8DCOqr1su8tmHBgU24FQ2k0Q.Z0TwS11XPqako9i',
        '2023-05-24',
        '7bdf2f58-17cd-4243-957e-1a3119ff53ad',
        'OFFLINE',
        '+37121234344',
        40.5,
        '77ecf268-36b8-438b-8130-333c2d735d37',
        'e1105616-383e-4e57-94da-f860bb08552d',
        '53da7e2b-7e7f-421e-8b5e-371dd13c2b64');

-- Insert data to test db delivery_zone

-- ChangeSet: insert test data into delivery_zone table 1
INSERT INTO delivery_zone (delivery_zone_id, delivery_zone_name, restaurants)
VALUES ('7bdf2f58-17cd-4243-957e-1a3119ff53ad', 'Berlin', 'Regattalex, AlimPizza, OleksandrKebab');

-- ChangeSet: insert test data into delivery_zone table 2
INSERT INTO delivery_zone (delivery_zone_id, delivery_zone_name, restaurants)
VALUES ('1ced69a9-4918-4ece-a06a-7c7996f4475a', 'Hamburg', 'Kalinka, Seljd, AntiWibe');

-- Insert data to test db order

-- ChangeSet: insert test data into order table 1
INSERT INTO "order" (order_id, courier_id, restaurant_id, order_status, placed_at, delivered_at)
VALUES ('d02ea287-0e06-407f-b1b2-f1cc26651420', '6453d453-0e33-41ec-aebd-637c5e6bb786', '86994b48-49a3-4fe9-862b-6da6bd9f869f', 'DELIVERED', '2023-01-16 12:30:45', '2022-01-16 12:56:40');

-- ChangeSet: insert test data into order table 2
INSERT INTO "order" (order_id, courier_id, restaurant_id, order_status, placed_at, delivered_at)
VALUES ('72dff74e-30f1-46cd-9693-42c34a4cac88', 'dca55a7d-a8db-4777-9daa-97c823277dbf', '63fbdcb5-6360-4af3-8701-858f7a4d6467', 'PLACED', '2023-02-16 15:36:12', '2023-02-16 16:30:34');

-- ChangeSet: update-order-status-delivered
UPDATE "order"
SET order_status = 'DELIVERED'
WHERE order_status = 'DELIVERED';

-- ChangeSet: update-order-status-placed
UPDATE "order"
SET order_status = 'PLACED'
WHERE order_status = 'PLACED';

-- Insert data to test db product

-- ChangeSet: insert test data into product table 1
INSERT INTO product (product_id, product_name, product_price, restaurant_id, description)
VALUES ('de2a3329-a046-4aef-ae9d-f4b811091f66', 'Pizza Carbonara', 14.99, '86994b48-49a3-4fe9-862b-6da6bd9f869f', 'abcde');

-- ChangeSet: insert test data into product table 2
INSERT INTO product (product_id, product_name, product_price, restaurant_id, description)
VALUES ('e4788d55-58d9-48bd-91f8-bcb3926f790d', 'Spring-roll', 15.87, '63fbdcb5-6360-4af3-8701-858f7a4d6467', 'abcabc');

-- ChangeSet: insert test data into product table 3
INSERT INTO product (product_id, product_name, product_price, restaurant_id, description)
VALUES ('f5b55666-9434-4d12-8343-3261aff2b183', 'Pita', 16.89, '29102365-460d-4301-8117-4e62441d9c7f', 'bcabcabca');

-- Insert data to test db restaurant

-- ChangeSet: insert test data into restaurant table 1
INSERT INTO restaurant (restaurant_id, restaurant_name, owner, app_role_id, restaurant_status, creation_date, delivery_zone_id)
VALUES ('86994b48-49a3-4fe9-862b-6da6bd9f869f', 'Pizza-Pasta', 'Marius Iceberg', '60c9bbdd-f631-414f-a12e-63ed1119b264', 'ACTIVE', '2021-06-24', '7bdf2f58-17cd-4243-957e-1a3119ff53ad');

-- ChangeSet: insert test data into restaurant table 2
INSERT INTO restaurant (restaurant_id, restaurant_name, owner, app_role_id, restaurant_status, creation_date, delivery_zone_id)
VALUES ('63fbdcb5-6360-4af3-8701-858f7a4d6467', 'Sushi-Rolli', 'Antony Tilt', '60c9bbdd-f631-414f-a12e-63ed1119b264', 'OFFLINE', '2013-06-24', '7bdf2f58-17cd-4243-957e-1a3119ff53ad');

-- ChangeSet: insert test data into restaurant table 3
INSERT INTO restaurant (restaurant_id, restaurant_name, owner, app_role_id, restaurant_status, creation_date, delivery_zone_id)
VALUES ('29102365-460d-4301-8117-4e62441d9c7f', 'Better Soi', 'Anatoly Bargel', '60c9bbdd-f631-414f-a12e-63ed1119b264', 'ACTIVE', '22021-08-14', '1ced69a9-4918-4ece-a06a-7c7996f4475a');

-- ChangeSet: update-restaurant-status-active
UPDATE restaurant
SET restaurant_status = 'ACTIVE'
WHERE restaurant_status = 'ACTIVE';

-- ChangeSet: update-restaurant-status-offline
UPDATE restaurant
SET restaurant_status = 'OFFLINE'
WHERE restaurant_status = 'OFFLINE';

-- Insert data to test db statistics

-- ChangeSet: insert test data into statistics table 1
INSERT INTO statistics (statistics_id, courier_id, start_date, till, completed_deliveries, earned_money)
VALUES ('25e65c2d-0d88-41fb-a9b1-085d3a126318', '6453d453-0e33-41ec-aebd-637c5e6bb786', '2021-06-24', '2021-07-24', 100, 423.36);

-- ChangeSet: insert test data into statistics table 2
INSERT INTO statistics (statistics_id, courier_id, start_date, till, completed_deliveries, earned_money)
VALUES ('89e23605-46a6-4e40-b48d-bb677fde1d2c', 'dca55a7d-a8db-4777-9daa-97c823277dbf', '2021-08-15', '2021-09-24', 130, 479.89);

-- ChangeSet: insert test data into statistics table 3
INSERT INTO statistics (statistics_id, courier_id, start_date, till, completed_deliveries, earned_money)
VALUES ('77ecf268-36b8-438b-8130-333c2d735d37', '8035c414-89a8-40e1-a914-83b65388a1f5', '2021-06-24', '2021-06-27', 114, 460.45);

-- Insert data to test db manager

-- ChangeSet: insert test data into manager table 1
INSERT INTO manager (manager_id, manager_name, app_role_id)
VALUES ('91069e9d-683b-4fd1-93bc-f135207b7e73', 'Alisa Welley', 'f6604fdd-71db-4e8c-a884-61d7de2b40cc');

-- ChangeSet: insert test data into manager table 2
INSERT INTO manager (manager_id, manager_name, app_role_id)
VALUES ('4829faa8-4946-410b-b859-f595d537e949', 'Alexei Treck', 'f6604fdd-71db-4e8c-a884-61d7de2b40cc');

-- ChangeSet: insert test data into manager table 3
INSERT INTO manager (manager_id, manager_name, app_role_id)
VALUES ('e1105616-383e-4e57-94da-f860bb08552d', 'Sandra Suar', 'f6604fdd-71db-4e8c-a884-61d7de2b40cc');

-- ChangeSet 1
ALTER TABLE courier
    ADD CONSTRAINT fk_courier_delivery_zone_id
        FOREIGN KEY (delivery_zone_id)
            REFERENCES delivery_zone (delivery_zone_id)
            ON DELETE CASCADE ON UPDATE CASCADE;

-- ChangeSet 2
ALTER TABLE courier
    ADD CONSTRAINT fk_courier_statistics_id
        FOREIGN KEY (statistics_id)
            REFERENCES statistics (statistics_id)
            ON DELETE CASCADE ON UPDATE CASCADE;

-- ChangeSet 3
ALTER TABLE courier
    ADD CONSTRAINT fk_courier_support_manager_id
        FOREIGN KEY (support_manager_id)
            REFERENCES manager (manager_id)
            ON DELETE CASCADE ON UPDATE CASCADE;

-- ChangeSet 4
ALTER TABLE courier
    ADD CONSTRAINT fk_courier_app_role_id
        FOREIGN KEY (app_role_id)
            REFERENCES app_role (app_role_id)
            ON DELETE CASCADE ON UPDATE CASCADE;

-- ChangeSet 5
ALTER TABLE manager
    ADD CONSTRAINT fk_manager_app_role_id
        FOREIGN KEY (app_role_id)
            REFERENCES app_role (app_role_id)
            ON DELETE CASCADE ON UPDATE CASCADE;

-- ChangeSet 6
ALTER TABLE "order"
    ADD CONSTRAINT fk_order_courier_id
        FOREIGN KEY (courier_id)
            REFERENCES courier (courier_id)
            ON DELETE CASCADE ON UPDATE CASCADE;

-- ChangeSet 7
ALTER TABLE "order"
    ADD CONSTRAINT fk_order_restaurant_id
        FOREIGN KEY (restaurant_id)
            REFERENCES restaurant (restaurant_id)
            ON DELETE CASCADE ON UPDATE CASCADE;

-- ChangeSet 8
ALTER TABLE product
    ADD CONSTRAINT fk_product_restaurant_id
        FOREIGN KEY (restaurant_id)
            REFERENCES restaurant (restaurant_id)
            ON DELETE CASCADE ON UPDATE CASCADE;

-- ChangeSet 9
ALTER TABLE restaurant
    ADD CONSTRAINT fk_restaurant_app_role_id
        FOREIGN KEY (app_role_id)
            REFERENCES app_role (app_role_id)
            ON DELETE CASCADE ON UPDATE CASCADE;

-- ChangeSet 10
ALTER TABLE restaurant
    ADD CONSTRAINT fk_restaurant_delivery_zone_id
        FOREIGN KEY (delivery_zone_id)
            REFERENCES delivery_zone (delivery_zone_id)
            ON DELETE CASCADE ON UPDATE CASCADE;

-- ChangeSet 11
ALTER TABLE statistics
    ADD CONSTRAINT fk_statistics_courier_id
        FOREIGN KEY (courier_id)
            REFERENCES courier (courier_id)
            ON DELETE CASCADE ON UPDATE CASCADE;
