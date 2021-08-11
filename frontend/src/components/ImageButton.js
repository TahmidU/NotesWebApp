import React from 'react';
import '../styles/image_button.css';
import { Link } from 'react-router-dom';

const SIZES = ['img-btn-small', 'img-btn-logo'];

export const ImageButton = ({image, alt, btnSize, isLink, to, className, onClick}) => {

    const checkBtnSize = SIZES.includes(btnSize) ? btnSize : SIZES[0];
    const checkTo = to == null ? '/' : to;
    const checkClassName = className == null ? '' : className;

    if(isLink){
        return(
            <Link className={`img-btn ${checkBtnSize} ${checkClassName}`} to={checkTo}>
                <img src={image} alt={alt}/>
            </Link>
        );
    }

    return(
        <button className={`img-btn ${checkBtnSize} ${checkClassName}`} onClick={onClick}>
            <img src={image} alt={alt}/>
        </button>
    )

}