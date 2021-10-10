import React from 'react';
import '../styles/image_button.css';
import '../styles/button.css';
import { Link } from 'react-router-dom';

const SIZES = ['img-btn-small', 'img-btn-logo'];
const STYLES = ['', 'btn-round-blue', 'btn-round-blue'];

export const ImageButton = (props) => {

    const checkBtnSize = SIZES.includes(props.btnSize) ? props.btnSize : SIZES[0];
    const checkBtnStyles = STYLES.includes(props.btnStyle) ? props.btnStyle : STYLES[0];
    const checkTo = props.to == null ? '/' : props.to;
    const checkClassName = props.className == null ? '' : props.className;

    if(props.isLink){

        if(typeof props.image === 'string'){
            return(
                <Link className={`img-btn ${checkBtnStyles} ${checkBtnSize} ${checkClassName}`} to={checkTo}>
                    <img src={props.image} alt={props.alt}/>
                </Link>
            );
        }

        return(
            <Link className={`img-btn ${checkBtnStyles} ${checkBtnSize} ${checkClassName}`} to={checkTo}>
                <div>{props.image}</div>
            </Link>
        );

    }

    if(typeof props.image === 'string'){
        return(
            <button className={`img-btn ${checkBtnStyles} ${checkBtnSize} ${checkClassName}`} to={checkTo}>
                <img src={props.image} alt={props.alt}/>
            </button>
        );
    }

    return(
        <button className={`img-btn ${checkBtnStyles} ${checkBtnSize} ${checkClassName}`} onClick={props.onClick}>
            <div>{props.image}</div>
        </button>
    )

}