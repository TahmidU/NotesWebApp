import React from 'react';
import '../styles/footer.css';
import LogoImg from '../img/logo_alt.svg';

export const Footer = () => (
    <footer>
        <div className='footer-section-one'>
            <div className='footer-logo-container'>
                <img src={LogoImg} alt='logo'/>
            </div>
            @2021 Tahmid Uddin
        </div>
        <div className='footer-section-two'>
            <ul className='footer-column'>
                <li className='footer-header'> Product </li>
                <li> Why Notes </li>
                <li> Regions </li>
                <li> Uptime </li>
                <li> Download App </li>
            </ul>
            <ul className='footer-column'>
                <li className='footer-header'> Features </li>
                <li> Free </li>
                <li> Sync </li>
                <li> Web Clipper </li>
                <li> Calendar </li>
                <li> Tasks </li>
                <li> Noteboard </li>
            </ul>
            <ul className='footer-column'>
                <li className='footer-header'> Community </li>
                <li> Our Community </li>
                <li> Beta Program </li>
                <li> Developers </li>
                <li> Forum </li>
            </ul>
            <ul className='footer-column'>
                <li className='footer-header'> Support </li>
                <li> Help </li>
                <li> Trouble Shooting </li>
                <li> Blog </li>
            </ul>
            <ul className='footer-column'>
                <li className='footer-header'> Company </li>
                <li> About Us </li>
                <li> Careers </li>
                <li> Contact Us </li>
            </ul>
        </div>
    </footer>
);
