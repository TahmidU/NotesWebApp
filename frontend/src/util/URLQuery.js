import { useLocation } from 'react-router';

export function useURLQuery(){
    return new URLSearchParams(useLocation().search);
}