import { useState, useEffect, React } from 'react';
import '../styles/theme.css';
import '../styles/navbar.css';
import '../styles/button.css';
import LogoImg from '../img/logo.svg';
import HamburgerImg from '../img/hamburger.svg';
import CrossImg from '../img/cross.svg';
import { Button } from './Button';
import { ImageButton } from './ImageButton';

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

    function handleOnLinkClick(){

        var checkbox = document.getElementById('nav-checkbox');
        checkbox.checked = false;
        document.body.style.position = '';
        setCheckboxState(false);

    }

    return(
        <div>
        
            <nav>

                <ImageButton image={LogoImg} alt='logo' btnSize='img-btn-logo' className='nav-logo-container' isLink={true}/>

                <input id='nav-checkbox' type='checkbox' onChange={handleCheckboxOnChange}/>
                <div className='hamburger-icon-container'>
                    <img src={isCheckboxChecked ? CrossImg : HamburgerImg} alt='hamburger'/>
                </div>

                <div className='nav-options'>
                        <ul className='test'>
                            <Button onClick={handleOnLinkClick} isLink={true} btnSize='btn-medium' className='nav-link'>Home</Button>
                            <Button onClick={handleOnLinkClick} isHashLink={true} btnSize='btn-medium' className='nav-link' to='/#about'>About</Button>
                            <Button onClick={handleOnLinkClick} isLink={true} btnSize='btn-medium' className='nav-link'>Support</Button>
                        </ul>
                </div>

                <Button className='nav-btn' btnSize='btn-medium' btnStyle='btn-round-blue' to='/noteboard' isLink={true}>Get Started</Button>

            </nav>

        </div>
    );

        
    
};