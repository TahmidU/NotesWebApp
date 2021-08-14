import React from 'react';
import '../styles/layout.css';
import { Navbar } from './Navbar';
import { Footer } from './Footer';

export const Layout = ({children}) => (

    <>
        <Navbar/>
        <div className='main-body'>
            <div style={{width: '100%', height: '100%'}}>
                {children}
            </div>
            <Footer/>
        </div>
    </>

);