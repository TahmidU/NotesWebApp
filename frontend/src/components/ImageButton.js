import React, { useState } from 'react';
import { Link } from 'react-router-dom';

export const ImageButton = ({toggleImages, onClick}) => {

    const [isClicked, setClicked] = useState(false);
    
    // Array that should consist of length 2. Toggles between index 0 (default) and 1.
    const checkToggleImages = toggleImages;

    function handleOnClick(){

        onClick();
    }

    return(
        <button onClick={handleOnClick}>
            <img src={checkToggleImages[0]}/>
        </button>
    )

}