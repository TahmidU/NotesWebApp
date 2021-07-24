import React from 'react';
import { Navbar } from './Navbar';
import { Footer } from './Footer';

export const Layout = ({children}) => (

    <>
        <Navbar/>
        <div className='main_body' style={{width: '100%', height: '100vh'}}>
            {children}
        </div>
        <Footer/>
    </>

);