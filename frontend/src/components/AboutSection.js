import '../styles/about_section.css';
import FreeImg from '../img/about_free.svg';
import MapImg from '../img/about_map.svg';
import DesignImg from '../img/about_design.svg';
import OrganisedImg from '../img/about_organised.svg';


export const AboutSection = () => {

    return(
        <div id='about' className='about-container'>
            <h1 className='large-text'>About</h1>
            <div className='grid-layout'>
                <div className='grid-box'>
                    <div className='grid-image-container'>
                        <img src={FreeImg} alt='free'/>
                    </div>
                    <p className='grid-title medium-bold-text'>It is Free!</p>
                    <p className='grid-detail'>Create notes and have access to unlimited storage.</p>
                </div>
                <div className='grid-box'>
                    <div className='grid-image-container'>
                        <img src={MapImg} alt='map'/>
                    </div>
                    <p className='grid-title medium-bold-text'>Access from Anywhere!</p>
                    <p className='grid-detail'>Your notes stay up-to-date and can be accessed from any part of the world {'('}internet connection required{')'}.</p>
                </div>
                <div className='grid-box'>
                    <div className='grid-image-container'>
                        <img src={DesignImg} alt='design'/>
                    </div>
                    <p className='grid-title medium-bold-text'>Powerful Editor!</p>
                    <p className='grid-detail'>Use one of the most powerful web text editor available. </p>
                </div>
                <div className='grid-box'>
                    <div className='grid-image-container'>
                        <img src={OrganisedImg} alt='organised'/>
                    </div>
                    <p className='grid-title medium-bold-text'>Organised!</p>
                    <p className='grid-detail'>All your notes; organised and online 24/7.</p>
                </div>
            </div>
        </div>
    );

}