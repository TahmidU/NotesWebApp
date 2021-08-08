import React, { useRef, useEffect, useState } from 'react';
import '../styles/testimony_section.css';
import ForbesImg from '../img/forbes_logo.png';
import WellGoodImg from '../img/well&good_logo.png';
import IncImg from '../img/inc_logo.png';

export const TestimonySection = () => {

    const reviewers = ['Forbes', 'Well + Good', 'Inc. Magazine'];
    const reviewersQuotes = [
    `"Notes is a powerful tool that can help executives,\
    entrepreneurs and creative people capture and arrange their ideas.\
    All you have to do is use it."`,
    `"It feels like there are endless ways to use Notes… Use it for school, work, life, and beyond."`,
    `"Notes is a powerful tool for managing your tasks right\
    alongside all of the information you work with every day."`
    ];

    const count = useRef(0);
    const [quote, setQuote] = useState({
        reviewer: reviewers[0],
        reviewerQuote: reviewersQuotes[0]
    });

    useEffect(() => {
        
        const interval = setInterval(() => {
            
            count.current = (count.current + 1) % 3;

            setQuote({
                reviewer: reviewers[count.current],
                reviewerQuote: reviewersQuotes[count.current]
            });

        }, 6000);
        
        return () => clearInterval(interval);

    });

    return(
        <div className='testimony-container'>
            <h1>Testimony</h1>
            <div className='testimony-content'>
                <div className='testimony-quote'>
                    {quote.reviewerQuote}
                </div>
                <div className='testimony-reviewer'>
                    {quote.reviewer}
                </div>
            </div>
            <div className='testimony-reviewers-logos'>
                <div className='reviewers-logos'>
                    <img src={ForbesImg} alt='forbes' />
                </div>
                <div className='reviewers-logos'>
                    <img src={WellGoodImg} alt='wellgood' />
                </div>
                <div className='reviewers-logos'>
                    <img src={IncImg} alt='inc' />
                </div>
            </div>
        </div>
    );
}

