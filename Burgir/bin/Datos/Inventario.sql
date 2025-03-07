CREATE DATABASE Inventario;

USE Inventario;

CREATE TABLE Ingredientes (
    id_ing INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(30) NOT NULL,
    precio FLOAT NOT NULL
);

CREATE TABLE Producto (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(30) NOT NULL,
    detalles VARCHAR(250),
    precio FLOAT NOT NULL,
    imagen VARCHAR(100)
);

CREATE TABLE Producto_Ingredientes (
    id_producto INT,
    id_ingrediente INT,
    cantidad INT NOT NULL,
    PRIMARY KEY (id_producto, id_ingrediente),
    FOREIGN KEY (id_producto) REFERENCES Producto(id) ON DELETE CASCADE,
    FOREIGN KEY (id_ingrediente) REFERENCES Ingredientes(id_ing) ON DELETE CASCADE
);

CREATE TABLE Pedido (
    id_pedido INT AUTO_INCREMENT PRIMARY KEY,
    estado VARCHAR(20) DEFAULT 'activo'
);

CREATE TABLE Pedido_Productos (
    id_pedido INT NOT NULL,
    id_producto INT NOT NULL,
    id_unico_producto INT NOT NULL,
    precio INT NOT NULL,
    PRIMARY KEY (id_pedido, id_producto, id_unico_producto),
    UNIQUE KEY (id_pedido, id_unico_producto),
    FOREIGN KEY (id_pedido) REFERENCES Pedido(id_pedido) ON DELETE CASCADE,
    FOREIGN KEY (id_producto) REFERENCES Producto(id) ON DELETE CASCADE
);

CREATE TABLE Ingredientes_Extras (
    id_pedido INT NOT NULL,
    id_unico_producto INT NOT NULL,
    id_ingrediente INT NOT NULL,
    cantidad FLOAT NOT NULL,
    PRIMARY KEY (id_pedido, id_unico_producto, id_ingrediente),
    FOREIGN KEY (id_pedido, id_unico_producto) REFERENCES Pedido_Productos(id_pedido, id_unico_producto) ON DELETE CASCADE,
    FOREIGN KEY (id_ingrediente) REFERENCES Ingredientes(id_ing) ON DELETE CASCADE
);

INSERT INTO Ingredientes (nombre, precio) VALUES
("Pan de Hamburguesa", 5.0),
("Carne", 20.0),
("Queso amarillo", 10.0),
("Lechuga", 5.0),
("Aros de cebolla", 3.0),
("Tomate", 4.0),
("Ketchup", 2.0),
("Mostaza", 2.0),
("Pollo empanizado", 15.0),
("Tocino", 15.0),
("Salsa BBQ", 8.0),
("Salsa especial", 10.0),
("Hamburguesa de vegetales", 18.0),
("Jalapeños", 6.0),
("Salsa picante", 5.0),
("Piña", 7.0);

INSERT INTO Producto (nombre, detalles, precio, imagen) VALUES
("Hamburguesa Clasica", "Incluye pan, carne, queso amarillo, lechuga, aros de cebolla, tomate, ketchup y mostaza", 80.0,"src\\Presentacion\\rb_2151137700.png"),
("Hamburguesa de Pollo", "Incluye pan, pollo empanizado, queso amarillo, lechuga, aros de cebolla, tomate y mostaza", 75.0, "src\\Presentacion\\rb_2151137700.png"),
("Hamburguesa de Tocino", "Incluye pan, carne, queso amarillo, tocino, lechuga, aros de cebolla, tomate y salsa BBQ", 100.0, "src\\Presentacion\\rb_2151137700.png"),
("Hamburguesa Deluxe", "Incluye pan, doble carne, doble queso amarillo, tocino, lechuga, aros de cebolla, tomate y salsa especial", 120.0, "src\\Presentacion\\rb_2151137700.png"),
("Hamburguesa Vegetariana", "Incluye pan, queso amarillo, lechuga, aros de cebolla, tomate y hamburguesa de vegetales", 75.0, "src\\Presentacion\\rb_2151137700.png"),
("Hamburguesa Jalapeña", "Incluye pan, carne, queso amarillo, jalapeños, lechuga, aros de cebolla, tomate, ketchup, mostaza y salsa picante", 100.0, "src\\Presentacion\\rb_2151137700.png"),
("Hamburguesa Hawaiana", "Incluye pan, carne, queso amarillo, piña, lechuga, aros de cebolla, tomate, ketchup, mostaza y tocino", 90.0, "src\\Presentacion\\rb_2151137700.png"),
("Hamburguesa Texana", "Incluye pan, carne, queso amarillo, aros de cebolla, tocino y salsa BBQ", 80.0, "src\\Presentacion\\rb_2151137700.png"),
("Hamburguesa Infantil", "Incluye pan, carne, queso amarillo, y ketchup", 70.0, "src\\Presentacion\\rb_2151137700.png"),
("Hamburguesa Doble", "Incluye pan, doble carne, queso amarillo, lechuga, aros de cebolla, tomate, ketchup y mostaza", 80.0, "src\\Presentacion\\rb_2151137700.png");

INSERT INTO Producto_Ingredientes(id_producto, id_ingrediente, cantidad) VALUES
-- Hamburguesa clasica
(1, 1, 2),
(1, 2, 1),
(1, 3, 1),
(1, 4, 1),
(1, 5, 2),
(1, 6, 1),
(1, 7, 1),
(1, 8, 1),
-- Hamburguesa de Pollo
(2, 1, 2),
(2, 9, 1),
(2, 3, 1),
(2, 4, 1),
(2, 5, 2),
(2, 6, 1),
(2, 8, 1),
-- Hamburguesa de Tocino
(3, 1, 2),
(3, 2, 1),
(3, 3, 1),
(3, 10, 2),
(3, 4, 1),
(3, 5, 2),
(3, 6, 1),
(3, 11, 1),
-- Hamburguesa Deluxe
(4, 1, 2),
(4, 2, 2),
(4, 3, 2),
(4, 10, 1),
(4, 4, 1),
(4, 5, 2),
(4, 6, 1),
(4, 12, 1),
-- Hamburguesa Vegetariana
(5, 1, 2),
(5, 3, 1),
(5, 4, 1),
(5, 5, 2),
(5, 6, 1),
(5, 13, 1),
-- Hamburguesa Jalapeña
(6, 1, 2),
(6, 2, 1),
(6, 3, 1),
(6, 14, 3),
(6, 4, 1),
(6, 5, 2),
(6, 6, 1),
(6, 7, 1),
(6, 8, 1),
(6, 15, 1),
-- Hamburguesa Hawaiana
(7, 1, 2),
(7, 2, 1),
(7, 3, 1),
(7, 16, 1),
(7, 4, 1),
(7, 5, 2),
(7, 6, 1),
(7, 7, 1),
(7, 8, 1),
(7, 10, 1),
-- Hamburguesa Texana
(8, 1, 2),
(8, 2, 1),
(8, 3, 1),
(8, 5, 2),
(8, 10, 1),
(8, 11, 1),
-- Hamburguesa Infantil
(9, 1, 2),
(9, 2, 1),
(9, 3, 1),
(9, 7, 1),
-- Hamburguesa Doble
(10, 1, 2),
(10, 2, 2),
(10, 3, 1),
(10, 4, 1),
(10, 5, 2),
(10, 6, 1),
(10, 7, 1),
(10, 8, 1);