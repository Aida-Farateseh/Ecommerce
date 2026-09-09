import '../../../../App.css';
import { Menu, MenuButton, MenuItem, MenuItems } from "@headlessui/react";
import React from "react";
import { Link } from "react-router-dom";

interface StorefrontProps {
    onSearch?: (query: string) => void
}

export function Storefront({ onSearch }: StorefrontProps) {
    return (
        <div>
            <nav className="navbar">
                <span>MyShop</span>
                <div className="navbar-links">
                    <Menu as="div" className="relative ml-3">
                        <MenuButton className="text-gray-300 hover:text-white text-sm">
                            Dam
                        </MenuButton>
                        <MenuItems className="absolute left-0 z-10 mt-2 w-48 origin-top-left rounded-md bg-white py-1 shadow-lg">
                            <MenuItem>
                                <Link to="/" className="block px-4 py-2 text-sm text-gray-700 hover:bg-gray-100">
                                    Klänningar
                                </Link>
                            </MenuItem>
                            <MenuItem>
                                <Link to="/" className="block px-4 py-2 text-sm text-gray-700 hover:bg-gray-100">
                                    Kjolar
                                </Link>
                            </MenuItem>
                            <MenuItem>
                                <Link to="/" className="block px-4 py-2 text-sm text-gray-700 hover:bg-gray-100">
                                    Blusar
                                </Link>
                            </MenuItem>
                            <MenuItem>
                                <Link to="/" className="block px-4 py-2 text-sm text-gray-700 hover:bg-gray-100">
                                    Byxor
                                </Link>
                            </MenuItem>
                            <MenuItem>
                                <Link to="/" className="block px-4 py-2 text-sm text-gray-700 hover:bg-gray-100">
                                    Underkläder
                                </Link>
                            </MenuItem>
                        </MenuItems>
                    </Menu>

                    <Menu as="div" className="relative ml-3">
                        <MenuButton className="text-gray-300 hover:text-white text-sm">
                            Man
                        </MenuButton>
                        <MenuItems className="absolute left-0 z-10 mt-2 w-48 origin-top-left rounded-md bg-white py-1 shadow-lg">
                            <MenuItem>
                                <Link to="/" className="block px-4 py-2 text-sm text-gray-700 hover:bg-gray-100">
                                    Kostym
                                </Link>
                            </MenuItem>
                            <MenuItem>
                                <Link to="/" className="block px-4 py-2 text-sm text-gray-700 hover:bg-gray-100">
                                    Skjorta
                                </Link>
                            </MenuItem>
                            <MenuItem>
                                <Link to="/" className="block px-4 py-2 text-sm text-gray-700 hover:bg-gray-100">
                                    T-shirt
                                </Link>
                            </MenuItem>
                            <MenuItem>
                                <Link to="/" className="block px-4 py-2 text-sm text-gray-700 hover:bg-gray-100">
                                    Byxor
                                </Link>
                            </MenuItem>
                            <MenuItem>
                                <Link to="/" className="block px-4 py-2 text-sm text-gray-700 hover:bg-gray-100">
                                    Underkläder
                                </Link>
                            </MenuItem>
                        </MenuItems>
                    </Menu>

                    <Menu as="div" className="relative ml-3">
                        <MenuButton className="text-gray-300 hover:text-white text-sm">
                            Barn
                        </MenuButton>
                        <MenuItems className="absolute left-0 z-10 mt-2 w-48 origin-top-left rounded-md bg-white py-1 shadow-lg">
                            <MenuItem>
                                <Link to="/" className="block px-4 py-2 text-sm text-gray-700 hover:bg-gray-100">
                                    Bebis
                                </Link>
                            </MenuItem>
                            <MenuItem>
                                <Link to="/" className="block px-4 py-2 text-sm text-gray-700 hover:bg-gray-100">
                                    Pojke
                                </Link>
                            </MenuItem>
                            <MenuItem>
                                <Link to="/" className="block px-4 py-2 text-sm text-gray-700 hover:bg-gray-100">
                                    Flicka
                                </Link>
                            </MenuItem>
                        </MenuItems>
                    </Menu>
                </div>
                <div className="navbar-actions"></div>
            </nav>

            <section className="hero-section">
                <h1>Sommarrean är här</h1>
                <p>Upp till 50% rabatt på utvalda produkter</p>
                <button>Handla nu</button>
            </section>

            <section className="product-section">
                <h2>Populära produkter</h2>
                <div className="product-grid"></div>
            </section>

            <footer className="footer">
                <p>© 2026 MyShop</p>
            </footer>
        </div>
    );
}