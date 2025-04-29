CREATE DATABASE IF NOT EXISTS proyecto;
USE proyecto;

-- Tabla PROVINCIA
CREATE TABLE provincia (
    codigo VARCHAR(10) PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL
);

-- Tabla CIUDAD
CREATE TABLE ciudad (
    codigo VARCHAR(10) PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    codigoProv VARCHAR(10),
    FOREIGN KEY (codigoProv) REFERENCES provincia(codigo)
);

-- Tabla USUARIO
CREATE TABLE usuario (
    nombre VARCHAR(50) PRIMARY KEY,
    clave VARCHAR(100) NOT NULL,
    estado VARCHAR(50),
    permisos VARCHAR(50)
);

INSERT INTO usuario (nombre, clave, estado, permisos)
VALUES ('root', 'DqwBwDN0RttQ0IJfOaXYng==', 'activo', '111111111');

-- Tabla CLIENTE
CREATE TABLE cliente (
    cedula VARCHAR(20) PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    direccion VARCHAR(200),
    codigoProv VARCHAR(10),
    codigoCiu VARCHAR(10),
    FOREIGN KEY (codigoProv) REFERENCES provincia(codigo),
    FOREIGN KEY (codigoCiu) REFERENCES ciudad(codigo)
);

-- Tabla PROVEEDOR
CREATE TABLE proveedor (
    ruc VARCHAR(20) PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    direccion VARCHAR(200),
    telefono VARCHAR(20),
    codigoProv VARCHAR(10),
    codigoCiu VARCHAR(10),
    FOREIGN KEY (codigoProv) REFERENCES provincia(codigo),
    FOREIGN KEY (codigoCiu) REFERENCES ciudad(codigo)
);

-- Tabla PRODUCTO
CREATE TABLE producto (
    codigo VARCHAR(20) PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    rucProveedor VARCHAR(20),
    FOREIGN KEY (rucProveedor) REFERENCES proveedor(ruc)
);

-- Tabla COMPRA
CREATE TABLE compra (
    codigo VARCHAR(20) PRIMARY KEY,
    fecha DATE NOT NULL,
    proveedor VARCHAR(20),
    total DECIMAL(10, 2),
    FOREIGN KEY (proveedor) REFERENCES proveedor(ruc)
);

-- Tabla VENTA
CREATE TABLE venta (
    codigo VARCHAR(20) PRIMARY KEY,
    fecha DATE NOT NULL,
    cliente VARCHAR(20),
    total DECIMAL(10, 2),
    FOREIGN KEY (cliente) REFERENCES cliente(cedula)
);

-- Tabla KARDEX
CREATE TABLE kardex (
    id INT AUTO_INCREMENT PRIMARY KEY,
    producto VARCHAR(20),
    fecha DATE NOT NULL,
    codigoC VARCHAR(20), -- puede referenciar a compra
    codigoV VARCHAR(20), -- puede referenciar a venta
    costoU DECIMAL(10,2),
    entrada INT,
    salida INT,
    inventario INT,
    FOREIGN KEY (producto) REFERENCES producto(codigo),
    FOREIGN KEY (codigoC) REFERENCES compra(codigo),
    FOREIGN KEY (codigoV) REFERENCES venta(codigo)
);
