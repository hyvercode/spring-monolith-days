import { useSignal } from '@vaadin/hilla-react-signals';
import type { ViewConfig } from '@vaadin/hilla-file-router/types.js';


export const config: ViewConfig = {
  menu: {
    title: 'Dashboard',
  },
};

export default function MainView() {
  const name = useSignal('');
  return (
    <div className="flex flex-col items-start gap-m">
      
    </div>
  );
}
