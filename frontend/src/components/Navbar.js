import { useState, useEffect, React } from 'react';
import '../styles/theme.css';
import '../styles/navbar.css';
import '../styles/button.css';
import LogoImg from '../img/logo.svg';
import HamburgerImg from '../img/hamburger.svg';
import CrossImg from '../img/cross.svg';
import { Link } from 'react-router-dom';
import { HashLink } from 'react-router-hash-link';
import { Button } from './Button';

export const Navbar = () => {

    const [isCheckboxChecked, setCheckboxState] = useState(false);

    useEffect(() => {

        window.addEventListener('resize', handleWindowResize);
        return () => {
            window.removeEventListener('resize', handleWindowResize)
        }
    });

    function handleWindowResize(event){
        if(window.innerWidth > 768){

            document.body.style.position = '';
            
        }else if(window.innerWidth <= 768 && isCheckboxChecked){
            
            document.body.style.position = 'fixed';
            
        }

    }

    function handleCheckboxOnChange(event){

        const isChecked = event.target.checked;
        
        if(isChecked !== isCheckboxChecked){
            setCheckboxState(isChecked);
        }

        if(isChecked){

            document.body.style.position = 'fixed';

        }else{

            document.body.style.position = '';
            
        }

    }

    return(
        <div>
        
            <nav>

                <Link className='nav-brand' to='/'>
                    <div className='nav-logo-container'>
                        <img src={LogoImg} alt='logo'/>
                    </div>
                </Link>

                <input type='checkbox' onChange={handleCheckboxOnChange}/>
                <div className='hamburger-icon-container'>
                    <img src={isCheckboxChecked ? CrossImg : HamburgerImg} alt='hamburger'/>
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

        
    
};