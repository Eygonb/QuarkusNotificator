CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE IF NOT EXISTS notification_addresses
(
    id uuid NOT NULL DEFAULT uuid_generate_v4 (),
    user_id text NOT NULL,
    address text,
    type text,
    CONSTRAINT "notification_addresses_pkey" PRIMARY KEY (id)
);