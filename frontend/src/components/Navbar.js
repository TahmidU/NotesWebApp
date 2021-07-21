import React from 'react';
import '../styles/theme.css';
import '../styles/navbar.css';
import '../styles/button.css';
import '../styles/hamburger.css';
import LogoImg from '../img/logo.svg';
import { Link } from 'react-router-dom';
import { HashLink } from 'react-router-hash-link';
import { Button } from './Button';

export const Navbar = () => (

    <div>
    
        <nav>

            <Link className='nav-brand' to='/'>
                <div className='nav-logo-container'>
                    <img src={LogoImg} alt='logo'/>
                </div>
            </Link>

            <input type="checkbox"/>
            <div className='hamburger-icon-container'>

                <svg width="100%" height="100%" viewBox="0 0 32 32" version="1.1" xmlns="http://www.w3.org/2000/svg" xmlnsXlink="http://www.w3.org/1999/xlink" xmlSpace="preserve" xmlnsSerif="http://www.serif.com/">
                    <g transform="matrix(1,0,0,1,-0.365947,2.96823)">
                        <path id="barOne" d="M28.198,6.851C28.198,6.526 27.934,6.262 27.609,6.262L5.123,6.262C4.798,6.262 4.534,6.526 4.534,6.851C4.534,7.177 4.798,7.441 5.123,7.441L27.609,7.441C27.934,7.441 28.198,7.177 28.198,6.851Z"/>
                    </g>
                    <g transform="matrix(1,0,0,1,-0.365947,9.10801)">
                        <path id="barTwo" d="M28.198,6.851C28.198,6.526 27.934,6.262 27.609,6.262L5.123,6.262C4.798,6.262 4.534,6.526 4.534,6.851C4.534,7.177 4.798,7.441 5.123,7.441L27.609,7.441C27.934,7.441 28.198,7.177 28.198,6.851Z"/>
                    </g>
                    <g transform="matrix(1,0,0,1,-0.365947,15.3291)">
                        <path id="barThree" d="M28.198,6.851C28.198,6.526 27.934,6.262 27.609,6.262L5.123,6.262C4.798,6.262 4.534,6.526 4.534,6.851C4.534,7.177 4.798,7.441 5.123,7.441L27.609,7.441C27.934,7.441 28.198,7.177 28.198,6.851Z"/>
                    </g>
                </svg>

            </div>

            <div className='nav-options'>
                    <ul className='test'>
                        <Link className='nav-link' to='/'>Home</Link>
                        <HashLink className='nav-link' smooth to='/#about'>About</HashLink>
                        <Link className='nav-link' to='/'>Support</Link>
                    </ul>
            </div>

            <Button btnSize='btn-medium' btnStyle='btn-round-blue' isNavBtn={true} to='/noteboard'>Get Started</Button>


            
        </nav>

    </div>

        
    
);