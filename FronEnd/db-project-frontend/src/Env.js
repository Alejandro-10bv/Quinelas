const { VITE_API_BASIC_URL } = import.meta.env;

const Env = {
    API_BASIC_URL: String(VITE_API_BASIC_URL)
};

export default Env;